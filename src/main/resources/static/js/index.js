$('ul li a').on('click', function (e){
    e.preventDefault();
    var url = this.href;
    $('.topnav a.active').remove("active");
    $(this).addClass('active');
    
    
    $(':main').remove();
    $(':main').load(url + 'main').hide().fadeIn('slow');
});
