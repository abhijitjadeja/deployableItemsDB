
var testing = false;
var testdata =
    {
        "releases":
        [
            { "release_date": "2017-10-10", "name": "2name", "description": "2desc", "key": "2", "status": "O" },
            { "release_date": "2017-10-10", "name": "wow", "description": "bow", "key": "9", "status": "O" },
            { "release_date": "2017-10-10", "name": "my", "description": "my", "key": "1", "status": "R" },
            { "release_date": "2017-10-10", "name": "test_mode", "description": "we are running in test mode", "key": "19", "status": "R" }
        ]
    };

function getReleaseData(f) {

    if (testing == true) {
        f(testdata);
    }
    else {
        $.getJSON("/api/release", function (data) {
            releases = { "releases": data };
            f(releases);
        });
    }
}


function applyTemplate(data) {
    var template = $('#template').html();
    //console.log(template);
    //console.log(data);
    Mustache.parse(template);   // optional, speeds up future uses
    var rendered = Mustache.render(template, data);
    //console.log(rendered);
    $('#release-table').html(rendered);
}

function init() {
    console.log('running');
    $("#release").hide();
    $("#release-href").on("click", function () {
        $("#release").show();
    })
    console.log('apply template');
    getReleaseData(applyTemplate);
    $("#add-release").on("click", function () {
        console.log('adding');
        addRelease($("#release-key").val(), $("#release-name").val(), $("#release-desc").val(), $("#release-date").val());
    })

}

function addRelease(key, name, desc, date) {
    $.ajax({
        type: "PUT",
        url: "/api/release/" + key,
        contentType: "application/json",
        data: '{"name":"' + name + '","description":"' + desc + '","date":"' + date + '"}'
    }).done(function () {
        $("#release-add").modal('toggle');
        getReleaseData(applyTemplate);
    })
        .fail(function (jqxhr, textStatus, error) {
            var err = textStatus + ", " + error;
            console.log("Request Failed: " + err);
            alert('failed');
        });
}