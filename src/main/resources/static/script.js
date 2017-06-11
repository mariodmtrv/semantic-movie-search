/**
 * Copyright 2017 (C) Endrotech
 * Created on :  6/11/2017
 * Author     :  Mario Dimitrov
 */
function generateMovieResult(movie) {
    var actors = movie.actors.join(',');
    var countries = movie.countries.join(',');
    var genres = movie.genres.join(',');
    var released = movie.released;
    var template = '\
<div class="col-sm-6 col-md-4">\
    <div class="thumbnail">\
    <img src="movie-icon.png" style="width: 25%" alt="Movie icon">\
    <div class="caption">\
    <h4>' + movie.title + '</h4>\
<p>\
<ul>\
<li><b>Starring:</b><span>' + actors + '</span></li>\
<li><b>Genre:</b> <span>' + genres + '</span></li>\
<li><b>Released:</b> <span>' + released + '</span></li>\
<li><b>Country:</b> <span>' + countries + '</span></li>\
</ul>\
</p>\
</div>\
</div>\
</div>';
    return template;
}

function findMovies() {
    var inputQuery = $("#search").find("input[name=search]").val();
    $.get("/find-movies?query=" + encodeURIComponent(inputQuery))
        .done(function (data) {
            displayMovies(data.movies);
            displayQuery(data.query);
        });
}
function displayMovies(movies) {
    movies.forEach(function (movie) {
        var result = generateMovieResult(movie);
        $("#moviesResults").append(result);
    })
}
function displayQuery(query) {
    var countries = query.countries.join(',');
    var genres = query.genres.join(',');
    var released = query.releasedRange;
    var actor = query.actor ? query.actor : "";
    var template = '<li>' + actor + '</li>' +
        '<li>' + countries + '</li>' +
        '<li>' + genres + '</li>' +
        '<li>' + released + '</li>';
    $("#queryDescription").append(template);
}

