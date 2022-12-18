function checkName(compositionName,id)
{
    $.ajax({
        url: 'checkcomposition',
        dataType: "json",
        method: "POST",
        async: false,
        // URL - сервлет
        data: {
            compositionId: id,
            compositionName: compositionName
        },
        success: function (response) {
            isNamePresented=response;
        }
    });
}

var isNamePresented =false;

function lockSubmit(id)
{
    let CompositionName = $(document.getElementById('CompositionName')).val();
    checkName(CompositionName,id);
    if(isNamePresented)
    {
        document.getElementById("errorPresented").innerHTML = "*Такое имя уже занято: "+CompositionName;
        document.getElementById("errorPresented").style.visibility = 'visible';
        document.getElementById("submit").disabled = true;
    }
    else {
        isNamePresented = false;
        document.getElementById("submit").disabled = false;
        document.getElementById("errorPresented").style.visibility = 'hidden';
    }
}