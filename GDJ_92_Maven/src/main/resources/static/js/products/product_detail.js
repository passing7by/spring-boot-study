/**
 * 
 */

// jsp에 연결되었나 확인
console.log('detail');

const actionEls1 = document.getElementsByClassName('action'); // 유사배열을 반환(배열X) => 배열에 사용할 수 있는 메서드를 사용할 수 없음
const actionEls2 = document.querySelectorAll('.action'); // 배열 반환 => 배열에 사용할 수 있는 메서드를 사용할 수 있음

// js 에서의 반복문들...
// for(초기식;조건식;증감식)
// for(a of actionEls1)
// for in
// foreach

const frm = document.querySelector('#frm');

const productNum = document.querySelector('#productNum').value;
let param = new URLSearchParams();
param.append("productNum", productNum);

for(a of actionEls1) {
	a.addEventListener("click", (e) => {
		let kind = e.target.getAttribute("data-kind");
		console.log(kind);
		
		if(kind === 'd') {
			frm.setAttribute('method', 'POST');
			frm.setAttribute('action', './delete');
			frm.submit();
		} else if(kind === 'u') {
			frm.setAttribute('action', './update');
			frm.submit();
		} else if(kind === 'c') {
			fetch('/member/cart', {
				method: 'post',
				body: param
			})
			.then(r => r.text())
			.then(r => {
				if(r > 0) {
					confirm('상품을 장바구니에 담았습니다.\n장바구니를 확인하시겠습니까?');
				} else {
					alert('상품을 장바구니에 담지 못했습니다.');
				}
			})
			.catch(r => alert('장바구니에 추가 중 문제 발생'));
			;
		} else {
			frm.setAttribute('method', 'POST');
			frm.setAttribute('action', '/account/add');
			frm.submit();
		}
	});
}

/*for(a of actionEls1) {
	a.addEventListener("click", function() {
		let kind = this.getAttribute("data-kind");
		console.log(kind);
	});
}*/

/*actionEls2.forEach(function(el) {
	el.addEventListener('click', function() {
		console.log('detail click');
		
		const kind = el.getAttribute('data-kind');
		
		if(kind){
			switch(kind) {
				case 'u':
					console.log('u');
					break;
				case 'd':
					console.log('d');
					break;
				default:
					console.log('another');
			}
		} else {
			console.log('data-kind 속성 없음');
		}
	});
});*/
