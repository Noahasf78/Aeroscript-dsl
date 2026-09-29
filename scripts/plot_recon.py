"""Regenerate the patrol SVG from verified simulator output (requires matplotlib)."""
from pathlib import Path
import math
import re
import subprocess
import matplotlib
matplotlib.use("Agg")
import matplotlib.pyplot as plt

ROOT = Path(__file__).resolve().parents[1]
result = subprocess.run(
    ["java", "-jar", "build/libs/aeroscript-1.0-all.jar", "examples/recon_patrol.aero"],
    cwd=ROOT, capture_output=True, text=True, check=True,
)
if result.stderr or "[TYPECHECK] Passed" not in result.stdout:
    raise RuntimeError(result.stderr or "Static checking did not pass")
pattern = r"\[(ASCEND|MOVE|DOCK)\] position=\(([-\d.]+), ([-\d.]+)\) \| altitude=([-\d.]+) m \| battery=([-\d.]+)%"
rows = [(kind, *map(float, values)) for kind, *values in re.findall(pattern, result.stdout)]
expected = [("ASCEND", 0, 0, 30), ("MOVE", 30, 40, 30), ("MOVE", 60, 0, 30), ("DOCK", 0, 0, 0)]
if [row[:4] for row in rows] != expected:
    raise RuntimeError("Unexpected patrol trajectory")
previous = (0, 0, 0)
distance = 0
telemetry = []
for kind, x, y, altitude, battery in rows:
    distance += math.hypot(x - previous[0], y - previous[1]) + abs(altitude - previous[2])
    if not math.isclose(battery, 100 - distance * 0.1, abs_tol=0.005):
        raise RuntimeError("Battery telemetry disagrees with simulated distance")
    telemetry.append((distance, battery))
    previous = (x, y, altitude)
if f"distance={distance:.2f} m | battery={rows[-1][-1]:.2f}%" not in result.stdout:
    raise RuntimeError("Summary differs from waypoint telemetry")

plt.rcParams.update({"font.family": "DejaVu Sans", "svg.fonttype": "none", "svg.hashsalt": "recon-patrol"})
bg, fg, muted, blue, green = "#0d1117", "#e6edf3", "#9da7b3", "#58a6ff", "#3fb950"
fig = plt.figure(figsize=(12, 7.5), facecolor=bg)
fig.text(.065, .945, "Triangular Recon Patrol", color=fg, fontsize=23, weight="bold")
fig.text(.065, .903, "Verified simulation  /  XY flight path in metres", color=muted, fontsize=11)
ax = fig.add_axes([.07, .22, .56, .64], facecolor=bg)
ax.set(xlim=(0, 70), ylim=(0, 50), xticks=range(0, 71, 10), yticks=range(0, 51, 10))
ax.set_aspect("equal", adjustable="box")
ax.set_xlabel("X (m)", color=muted, labelpad=10)
ax.set_ylabel("Y (m)", color=muted, labelpad=10)
ax.tick_params(colors=muted, length=0, pad=8)
for spine in ax.spines.values():
    spine.set_color("#30363d")
ax.grid(color="#252c35", linewidth=.7)
ax.plot([0, 30, 60], [0, 40, 0], color=blue, linewidth=2.5, zorder=3, label="Outbound")
ax.plot([60, 0], [0, 0], color=green, linewidth=2.5, linestyle=(0, (5, 4)), zorder=4, clip_on=False, label="Return to base")
for start, end, color in [((12, 16), (18, 24), blue), ((42, 24), (48, 16), blue), ((37, 0), (28, 0), green)]:
    ax.annotate("", xy=end, xytext=start, arrowprops=dict(arrowstyle="-|>", color=color, lw=2, mutation_scale=14), zorder=5, annotation_clip=False)
ax.scatter([0, 30, 60], [0, 40, 0], s=75, c=[green, blue, blue], edgecolors=bg, linewidths=1.5, zorder=6, clip_on=False)
ax.annotate("Base (0, 0)", (0, 0), xytext=(4, -38), textcoords="offset points", color=fg, fontsize=10)
ax.annotate("Alpha (30, 40)", (30, 40), xytext=(0, 15), textcoords="offset points", ha="center", color=fg, fontsize=10)
ax.annotate("Bravo (60, 0)", (60, 0), xytext=(0, -38), textcoords="offset points", ha="center", color=fg, fontsize=10)
legend = ax.legend(loc="upper left", frameon=False, fontsize=10)
for text in legend.get_texts(): text.set_color(fg)

def label(y, name, detail, lines, color):
    fig.text(.69, y, name, color=color, fontsize=14, weight="bold")
    fig.text(.69, y-.037, detail, color=muted, fontsize=10)
    for i, line in enumerate(lines):
        fig.text(.69, y-.084-i*.031, line, color=fg, fontsize=11)

label(.79, "BASE  (0, 0)", "Takeoff & Dock", [
    f"Takeoff  /  Alt: 30 m", f"{telemetry[0][0]:.2f} m  |  {telemetry[0][1]:.2f}% battery",
    f"Dock  /  Alt: 0 m", f"{telemetry[3][0]:.2f} m  |  {telemetry[3][1]:.2f}% battery"], green)
label(.51, "ALPHA  (30, 40)", "Alt: 30 m", [f"{telemetry[1][0]:.2f} m cumulative", f"{telemetry[1][1]:.2f}% battery"], blue)
label(.31, "BRAVO  (60, 0)", "Alt: 30 m", [f"{telemetry[2][0]:.2f} m cumulative", f"{telemetry[2][1]:.2f}% battery"], blue)
fig.text(.065, .083, f"TOTAL  {distance:.2f} m simulated distance    |    {rows[-1][-1]:.2f}% battery remaining", color=fg, fontsize=13, weight="bold")
fig.text(.065, .044, "160 m perimeter + 30 m ascent + 30 m descent. Distances are cumulative and include vertical travel.", color=muted, fontsize=10)
fig.savefig(ROOT / "docs/recon_flightpath.svg", facecolor=bg, metadata={"Date": None, "Title": "Triangular Recon Patrol", "Description": "Verified simulated route from Base to Alpha to Bravo and back. 220 metres total; 78 percent battery remaining."})
(ROOT / "build").mkdir(exist_ok=True)
fig.savefig(ROOT / "build/recon_flightpath.png", dpi=130, facecolor=bg)
print(result.stdout, end="")
print("Wrote docs/recon_flightpath.svg and build/recon_flightpath.png")
