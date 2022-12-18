function ajax() {
    $.ajax({
        url: 'ajaxrequest',
        // URL - сервлет
        data: {                 // передаваемые сервлету данные
            singerId: $(document.getElementById('Singerid')).val()
        },
        success: function (response) {
            // обработка ответа от сервера
            document.getElementById("SingerName").value = response;
        }
    });
}


var listNames =['ser','per','mer'];

function getNames(input){
    if(input !="")
        $.ajax({
            url: 'ajaxhandler',
            dataType: "json",
            data: {                 // передаваемые сервлету данные
                input: input
            },
            success: function(resultData){
                listNames = resultData;
            }
        });
}

function setOption(input){
    let res = "";
    inputG = input;
    getNames(input);
    for (var i = 0; i<listNames.length; i++)
    {
        res+= "<option>" + listNames[i] + "</option>";
    }
    console.log('update');
    document.getElementById("options").innerHTML = res;
}

function checkName(albumName,id)
{
    $.ajax({
        url: 'checkname',
        dataType: "json",
        method: "POST",
        async: false,
        // URL - сервлет
        data: {
            albumId: id,
            AlbumName: albumName
        },
        success: function (response) {
            isNamePresented=response;
        }
    });
}

var isNamePresented =false;

function lockSubmit(id)
{
    let albumName = $(document.getElementById('AlbumName')).val();
    checkName(albumName,id);
    if(isNamePresented)
    {
        document.getElementById("errorPresented").innerHTML = "*Такое имя уже занято: "+albumName;
        document.getElementById("errorPresented").style.visibility = 'visible';
        document.getElementById("submit").disabled = true;
    }
    else {
        isNamePresented = false;
        document.getElementById("submit").disabled = false;
        document.getElementById("errorPresented").style.visibility = 'hidden';
    }
}