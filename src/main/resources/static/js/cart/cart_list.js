/**
 * 
 */

/**
 * 전체 체크박스를 클릭했을 때 / 개별 체크박스를 클릭했을 때
 */
const checkAll = document.querySelector('#checkAll');
const checkBoxes = document.querySelectorAll('.ch');

checkAll.addEventListener('change', function() {
  // 전체 체크박스의 상태 변경시 개별 체크박스의 상태 변경
  checkBoxes.forEach(e => e.checked = checkAll.checked);
});

checkBoxes.forEach(e => {
  e.addEventListener('change', function() {
    // 개별 체크박스의 상태 변경시 전체 체크박스의 체크 해제
    checkAll.checked = false;

    // 개별 체크박스가 모두 체크되어 있는 경우 전체 체크박스에 체크
    // - 개별 체크박스가 체크될 때마다 모든 체크박스를 돌면서 체크 해제되어있는게 있는지 없는지 검사
    // - 만약 체크 해제되어있는게 있다면 전체 체크박스를 체크해제, 없다면 전체 체크박스를 체크
    let isAllChecked = true;
    checkBoxes.forEach(e => {
      if(!e.checked) {
        isAllChecked = false;
        // console.log("returned 1");
        return;
      }
    });
    // console.log("returned 2");
    checkAll.checked = isAllChecked;
  });
});

/**
 * 버튼 클릭시 서버에 요청
 */

const btns = document.querySelectorAll('.btn');
const frm = document.querySelector('#frm');


btns.forEach(e => {
	e.addEventListener('click', function() {
	  if(e.id === 'del') {
	    // /member/cartDelete 에 요청
	    frm.setAttribute('action', '/member/cartDelete');
	    frm.setAttribute('method', 'post');
	    frm.submit();
	  } else if(e.id === 'add'){
	    // /account/add 에 요청
	    frm.setAttribute('action', '/account/add');
	    frm.setAttribute('method', 'post');
	    frm.submit();
	  }
	});
});


