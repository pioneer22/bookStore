function htRem() {
  var width = document.documentElement.clientWidth;
  if (width > 750) {
    width = 750;
  }
  document.documentElement.style.fontSize = width / 7.5 + "px";
}
htRem();
window.onresize = function() {
  htRem();
};
