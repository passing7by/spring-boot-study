/**
 * 
 */

const btnAdd = document.querySelector('#add');
const resultDiv = document.querySelector('#result');
// const btnDels = document.querySelectorAll('del');

let count = 0;

btnAdd.addEventListener('click', function() {
  // resultDiv.innerHTML += 
  // `<div class="mb-3">
  //   <input type="file" name="attaches">
  //   <button class="del">X</button>
  // </div>`;
  // innerHTML은 추가가 아니라 기존의 내용을 지우고 덮어 쓰기 때문에 문제가 생김

  // 이미 파일 입력 폼이 5개 이상이라면 더 추가되지 않도록 하기 위해 아래의 코드를 실행하지 않음
  // if (resultDiv.children.length >= 5) {
  //   alert('최대 5개 가능');

  //   return;
  // }
  // 이런 방법도 있음
  count++;
  if (count > 5) {
    alert('최대 5개 가능');
    count--;
    
    return;
  }

  let newDiv = document.createElement('div');
  // newDiv.className = 'mb-3';
  newDiv.classList.add('mb-3');  // 클래스는 여러개를 가질 수 있으므로 classList.add()로 넣어주는 것이 좋음
  newDiv.classList.add('input-group');

  //let newInput = document.createElement('input');
  let ch = document.createElement('input');
  // newInput.type = 'file';
  // newInput.name = 'attaches';

  ch.setAttribute('type', 'file');
  ch.classList.add('form-control');
  ch.setAttribute('name', 'attaches');

  newDiv.appendChild(ch);

  // let newXBtn = document.createElement('button');
  ch = document.createElement('button');
  // newXBtn.type = 'button';
  // newXBtn.className = 'del';
  ch.setAttribute('type', 'button');
  ch.classList.add('del');
  ch.classList.add('btn');
  ch.classList.add('btn-danger');
  ch.innerText = 'X';

  newDiv.appendChild(ch);

  // xBtn.addEventListener('click', function(e) {
  //   console.log('xBtn click');
  //   this.parentElement.remove();

  //   e.stopPropagation(); // x 버튼을 눌렀을 때 이벤트 버블링이 일어나서 글 등록 버튼까지 눌러지는 것을 방지
  // });

  // console.log(newDiv);
  
  // add를 눌렀을 때 div가 한 번만 추가되고, add 버튼을 또 눌러도 더 추가되지 않도록
  // resultDiv.innerHTML = newDiv.getHTML();
  // add를 누를 때마다 계속 추가되도록
  resultDiv.appendChild(newDiv);
});

// const btnDels = document.querySelectorAll('del');

// xBtn.addEventListener('click', function(e) {
//   console.log('xBtn click');
//   this.parentElement.remove();

//   // e.stopPropagation(); // x 버튼을 눌렀을 때 이벤트 버블링이 일어나서 글 등록 버튼까지 눌러지는 것을 방지
// });

// 이벤트 위임으로 처리하는 방법
resultDiv.addEventListener('click', (e) => {
  // console.log(e.target);

  if(e.target.classList.contains('del')) {
    // console.log('xxxxxxx');
    e.target.parentElement.remove();

    count--;
  }
});

// jQuery로 이벤트 위임하는 방법
// on(이벤트명, 대상, 실행할 콜백함수)
  