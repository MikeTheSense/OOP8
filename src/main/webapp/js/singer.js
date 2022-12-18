function checkName(singerName,id)
{
    $.ajax({
        url: 'checksingername',
        dataType: "json",
        method: "POST",
        async: false,
        // URL - сервлет
        data: {
            singerId: id,
            singerName: singerName
        },
        success: function (response) {
            isNamePresented=response;
        }
    });
}

var isNamePresented =false;

function lockSubmit(id)
{
    let SingerName = $(document.getElementById('SingerName')).val();
    checkName(SingerName,id);
    if(isNamePresented)
    {
        document.getElementById("errorPresented").innerHTML = "*Такое имя уже занято: "+SingerName;
        document.getElementById("errorPresented").style.visibility = 'visible';
        document.getElementById("submit").disabled = true;
    }
    else {
        isNamePresented = false;
        document.getElementById("submit").disabled = false;
        document.getElementById("errorPresented").style.visibility = 'hidden';
    }
}