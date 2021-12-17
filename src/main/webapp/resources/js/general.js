/* global PF */

function loadHtmlToImage(contentType, fileName) {
    var container = document.getElementById('panelImageComplete:container');
    html2canvas(container).then(function (canvas) {
        var link = document.createElement("a");
        document.body.appendChild(link);
        link.download = fileName;
        link.href = canvas.toDataURL(contentType);
        link.target = '_blank';
        link.click();
    });
}
