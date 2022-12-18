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
            url: 'getSingerNames',
            dataType: "json",
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