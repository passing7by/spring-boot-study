/**
 * 
 */
const checkAll = document.querySelector('#checkAll');
const checkBoxes = document.querySelectorAll('.ch');

checkAll.addEventListener('change', function() {
  if (checkAll.checked) checkBoxes.forEach(e => e.checked = true);
  else checkBoxes.forEach(e => e.checked = false);
});

checkBoxes.forEach(e => {
  e.addEventListener('change', function() {
    checkAll.checked = false;
  });
});

