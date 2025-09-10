# 002: Screenshot Testing Tool: Compose Preview Screenshot Testing (CPST)

## Status

Accepted.

## Context

[Screenshot testing](https://developer.android.com/training/testing/ui-tests/screenshot)
is the recommended way to verify visual attributes in Compose UIs. The
[following](https://github.com/sergio-sastre/Android-screenshot-testing-playground)
[resources](https://github.com/takahirom/roborazzi?tab=readme-ov-file#paparazzi-and-roborazzi-a-comparison)
[were](https://medium.com/@natalia.kulbaka/comparing-snapshot-testing-libraries-paparazzi-roborazzi-compose-previews-screenshot-testing-b7c3b47f7f59)
[considered](https://slack-chats.kotlinlang.org/t/23121514/any-opinions-on-snapshot-testing-for-a-greenfield-project-on)
[when](https://github.com/android-ui-testing/Cookbook) choosing the right
screenshot testing tool.

## Decision

Tools that were seriously considered were
[Google's CPST](https://developer.android.com/studio/preview/compose-screenshot-testing),
[cashapp/paparazzi](https://github.com/cashapp/paparazzi),
[takahirom/roborazzi](https://github.com/takahirom/roborazzi),
[facebook/screenshot-tests-for-android](https://github.com/facebook/screenshot-tests-for-android).
CPST was chosen primarily because of the official Google support.

## Consequences

Fully described in
[the official documentation](https://developer.android.com/training/testing/ui-tests/screenshot#advantages).

Positive:

- A screenshot test does multiple assertions per test. For example, a single
  test can check colors, margins, sizes, and fonts.
- A screenshot test is much easier to write, understand, and maintain than an
  equivalent behavior test.
- They are especially useful when verifying and catching regressions on
  different screen sizes.

Negative:

- Dealing with the reference images can be cumbersome, as a big project might
  end up with thousands of PNGs.
- Different platforms (Linux, Max, and Windows) produce slightly different
  screenshots.
- They are slower than equivalent behavior tests.
- Having a large number of screenshot tests can cause problems, for example when
  a single change affects thousands of screenshots.

## Experience Report

**TODO**
