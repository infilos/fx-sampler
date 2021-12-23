# fx-sampler

This project is manually fork from [FxSampler](https://github.com/Col-E/FxSampler), with added github actions for distributing to multiple platforms, include MacOS, Windows and Linux. 

The github actions are cloned from [PhotoSlide](https://github.com/lanthale/PhotoSlide), another way is use the template of [maven-jpackage-template](https://github.com/wiverson/maven-jpackage-template).

In general, build and distribute a java/fx based GUI application is very easy.

## How to release

1. Push commit.
2. Push tag: `git tag v1.x` & `git push origin v1.x`
3. Create release manually on Github or with Github cli.

