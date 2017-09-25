# Country Picker Android
An android library for listing all the countries and user can select the country name, code, dial code and flag.

![screenshot](https://user-images.githubusercontent.com/13048080/29805132-34044c32-8cba-11e7-88d3-aefd8689151d.png)

## How to use
Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
Step 2. Add the dependency

```gradle
dependencies {
    compile 'com.github.yesterselga:CountryPickerAndroid:1.0.0'
}
```

## Usage

```java
CountryPicker picker = CountryPicker.newInstance("Select Country");  // dialog title
picker.setListener(new CountryPickerListener() {
    @Override
    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
        // Implement your code here
    }
});
picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
```
Done ;)
