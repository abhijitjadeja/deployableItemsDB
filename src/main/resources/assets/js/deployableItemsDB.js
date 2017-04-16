
var testing = true;
var testdata =
    {
        "releases":
        [
            { "release_date": "2017-10-10", "name": "2name", "description": "2desc", "key": "2", "status": "O" },
            { "release_date": "2017-10-10", "name": "wow", "description": "bow", "key": "9", "status": "O" },
            { "release_date": "2017-10-10", "name": "my", "description": "my", "key": "1", "status": "R" }
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


}