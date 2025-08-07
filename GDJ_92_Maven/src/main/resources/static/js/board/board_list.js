/**
 * 
 */

// 1. 뭘 클릭했을 때? 클릭이벤트 발동
const pn = document.querySelectorAll('.pn');
const pn2 = document.getElementsByClassName('pn');
const searchForm = document.getElementById('searchForm');
const pageNum = document.querySelector('#pageNum');

/*const prePageBtn = document.querySelector('.pn');
const pageNumBtn = document.querySelectorAll();
const nextPageBtn = document.querySelector();*/

console.log(pn);
console.log(pn2);

pn.forEach(function(item) {
/*	item.addEventListener('click', (e) => {
		console.log(e.target);

		let n = e.target.getAttribute('data-pn');
		pageNum.value = n;
		searchForm.submit();
	});*/
	// 페이지 화살표 버튼을 누를 때, a 태그 안의 span 태그를 누른 경우에는 작동이 안 되고 a태그를 눌러야만 작동됨
	// (두 경우에서 e.target으로 받아오는 요소가 다름)
	
	item.addEventListener('click', function() {
		console.log(this);
		
		let n = this.getAttribute('data-pn');
		pageNum.value = n;
		searchForm.submit();
	});
});
