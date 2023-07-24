/**
 * 경고창 . 숫자를 입력하세요. 
 */

function warning()
{
	let n1 = document.getElementById("num1").value; /*Name으로 한다면 Element's' Name은 배열로 값을 가져옴*/
	let n2 = document.getElementById("num2").value;
	
	if(isNaN(n1)||isNaN(n2)||n1==''||n2=='')
	{
		alert("숫자를 입력하세요");
		return false;
	}
	return true;
}