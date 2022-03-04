document.getElementById('shareBtn').onclick = function() {
    FB.ui({
        display: 'popup',
        method: 'share',
        href: 'http://127.0.0.1:8000/single-blog/13',
    }, function(response){});
}