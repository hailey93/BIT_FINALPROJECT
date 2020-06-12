window.onscroll = function () {
    myFunction()
};

var sticky = $('.sticky-container').offsetTop;

function myFunction() {
    if (window.pageYOffset > sticky) {
        $('.sticky-container').classList.add("fixed-header");
    } else {
        $('.sticky-container').classList.remove("fixed-header");
    }
}