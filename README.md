# Country Picker Android
An android library for listing all the countries and user can select the country name, code, dial code and flag.

![Alt text](https://github.com/yesterselga/country-picker-android/blob/master/screenshot-dark.png "Dark Theme")

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
    implementation 'com.github.yesterselga:country-picker-android:2.0'
}
```

## Usage

```java
CountryPicker picker = CountryPicker.newInstance("Select Country", Theme.DARK);  // dialog title and theme
picker.setListener(new CountryPickerListener() {
    @Override
    public void onSelectCountry(String name, String code, String dialCode, int flagDrawableResID) {
        
        // Implement your code here
        YOU_EDITTEXT.setText(dialCode);
        YOUR_IMAGE_VIEW.setImageResource(flagDrawableResID);

        picker.dismiss();
    }
});
picker.show(getSupportFragmentManager(), "COUNTRY_PICKER");
```
Done ;)
