// ボタン
const addButton = document.getElementById("addButton");
// リスト（親）
const tsk = document.getElementById("newTasks");



addButton.addEventListener("click", () => {
	const newLabel = document.createElement('label');
	  newLabel.textContent = '新規タスク：'; // ラベルのテキストを設定
	// 追加したい要素
	// 新しい input 要素を作成
	  const newInput = document.createElement('input');
	  newInput.type = 'text'; // input のタイプをテキストに設定
	  newInput.name = 'addTasks';
	  // ラベルの中に input を追加
	   newLabel.appendChild(newInput);
	 
	tsk.appendChild(newLabel);
});