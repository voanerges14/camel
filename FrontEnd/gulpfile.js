'use strict';

var gulp = require('gulp'),
    rename = require('gulp-rename'),
    notify = require('gulp-notify'),
    autoprefixer = require('gulp-autoprefixer'),
    livereload = require('gulp-livereload'),
    connect = require('gulp-connect'),
    sass = require('gulp-sass'),
    uncss = require('gulp-uncss'),
    minifyCSS = require('gulp-minify-css'),
    uglyfly = require('gulp-uglyfly'),
    babel = require('gulp-babel');

// server connect
gulp.task('connect', function() {
  connect.server({
    root: './',
    livereload: true
  });
});

// html
gulp.task('html', function() {
    gulp.src('index.html')
        .pipe(gulp.dest('../src/main/webapp/'))
        .pipe(connect.reload())
        .pipe(notify(' I did it! '));
});

// sass
gulp.task('sass', function() {
    gulp.src('src/scss/*.scss')
        .pipe(sass().on('error', sass.logError))
		.pipe(autoprefixer({
			browsers: ['last 2 versions'],
			cascade: false
        }))
        // .pipe(uncss({
        //     html: ['index.html']
        // }))
        // .pipe(minifyCSS())
        .pipe(rename('app.min.css'))
        .pipe(gulp.dest('../src/main/webapp/dist/css/'))
        .pipe(gulp.dest('dist/css/'))
        .pipe(notify(' I did it! '))
        .pipe(connect.reload());
});

// js
gulp.task('js', function () {
    gulp.src('src/js/*.js')
        .pipe(babel({
            presets: ['es2015']
        }))
        // .pipe(uglyfly())
        .pipe(rename('app.min.js'))
        .pipe(gulp.dest('../src/main/webapp/dist/js/'))
        .pipe(gulp.dest('dist/js/'))        
        .pipe(notify(' I did it! '))
        .pipe(connect.reload());
});

// watch
gulp.task('watch', function() {
    gulp.watch('src/scss/**/*.scss', ['sass'])
    gulp.watch('index.html', ['html'])
    gulp.watch('src/js/*.js', ['js'])
});

// default
gulp.task('default', ['connect', 'html', 'sass', 'js', 'watch']);