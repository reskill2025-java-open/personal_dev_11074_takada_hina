const hour = new Date().getHours();

if (4 <= hour && hour < 6) {
	document.getElementById("greeting").textContent = "早起きですね。";

}
else if (6 <= hour && hour < 8) {
	document.getElementById("greeting").textContent = "おはようございます！";

}
else if (8 <= hour && hour < 10) {
	document.getElementById("greeting").textContent = "今日も頑張りましょう！";

} else if (10 <= hour && hour < 12) {
	document.getElementById("greeting").textContent = "お腹が空きましたね。";
} else if (12 <= hour && hour < 13) {
	document.getElementById("greeting").textContent = "お昼はなにを食べましたか？";
} else if (13 <= hour && hour < 15) {
	document.getElementById("greeting").textContent = "眠くなる時間ですね。";

} else if (15 <= hour && hour < 16) {
	document.getElementById("greeting").textContent = "眠気は覚めましたか？";
} else if (16 <= hour && hour < 18) {
	document.getElementById("greeting").textContent = "あともう少し頑張りましょう！";
} else if (18 <= hour && hour < 22) {
	document.getElementById("greeting").textContent = "今日もお疲れさまでした！";

} else if (22 <= hour && hour < 1) {
	document.getElementById("greeting").textContent = "そろそろ寝る時間ですね。";
} else {

	document.getElementById("greeting").textContent = "今日は夜更かししますか？";


};
