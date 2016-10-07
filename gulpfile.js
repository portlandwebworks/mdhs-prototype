var gulp = require('gulp');
var sass = require('gulp-sass');
var jshint = require('gulp-jshint');
var lintspaces = require('gulp-lintspaces');
var stylish = require('jshint-stylish');
var flatten = require('gulp-flatten');
var filter = require("gulp-filter");
var sourcemap = require('gulp-sourcemaps');

gulp.task('convert-sass', function () {
	return gulp.src('src/main/resources/public/app/sass/*.scss')
			.pipe(sourcemap.init())
			.pipe(sass({
				outputStyle: 'compressed',
				includePaths: [
					'src/main/resources/public/bower_components/bootstrap-sass-official/assets/stylesheets'
				],
				sourcemaps: true
			}))
			.pipe(sourcemap.write('./'))
			.pipe(gulp.dest('src/main/resources/public/app/css/'));
});


gulp.task('scripts', function () {
	return gulp.src(['**/*.js'], {cwd: 'src/main/resources/public/app/'})
			.pipe(jshint())
			.pipe(jshint.reporter(stylish))
});

gulp.task('formatting', function () {
	return gulp.src(['public/**/*.js', 'public/**/*.scss', 'templates/**/*.html', '!public/bower_components/**'], {cwd: 'src/main/resources/'})
			.pipe(lintspaces({editorconfig: '.editorconfig'}))
			.pipe(lintspaces.reporter(stylish));
});

gulp.task('watch', function () {
	gulp.watch('src/main/resources/public/app/sass/**/*.scss', ['convert-sass']);
	gulp.watch(['src/main/resources/public/app/**/*.js'], ['scripts']);
});

gulp.task('default', ['scripts', 'formatting', 'convert-sass']);

