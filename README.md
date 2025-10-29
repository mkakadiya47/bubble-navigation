# Bubble Navigation 🎈

A beautiful and customizable bubble navigation bar for Android applications with smooth animations.

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)

## Features ✨

- 🎨 Customizable colors (background, icons, text)
- 🎭 Smooth bubble expand/collapse animations
- 📱 Material Design
- 🔧 Easy to integrate
- 💡 Lightweight library
- 🎯 Minimum SDK 21 (Android 5.0)

## Preview

The navigation bar features a bubble animation that expands to show the selected item's label, creating an elegant and intuitive user experience.

## Installation

### Step 1: Add JitPack repository

Add it in your root `build.gradle` at the end of repositories:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the dependency

```gradle
dependencies {
    implementation 'com.github.mkakadiya47:bubble-navigation:1.0.0'
}
```

## Usage

### XML Layout

Add `BubbleNavigationView` to your layout:

```xml
<com.mkakadiya.bubblenavigation.BubbleNavigationView
    android:id="@+id/bubbleNavigation"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_alignParentBottom="true"
    app:backgroundColor="#FFFFFF"
    app:selectedIconColor="#2196F3"
    app:unselectedIconColor="#757575"
    app:selectedTextColor="#2196F3"
    app:navigationElevation="8dp" />
```

### Java/Kotlin Code

Initialize and add navigation items:

```java
BubbleNavigationView bubbleNavigation = findViewById(R.id.bubbleNavigation);

// Add navigation items
bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Home", R.drawable.ic_home));
bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Search", R.drawable.ic_search));
bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Favorites", R.drawable.ic_favorite));
bubbleNavigation.addNavigationItem(new BubbleNavigationItem("Profile", R.drawable.ic_profile));

// Set navigation listener
bubbleNavigation.setOnNavigationItemSelectedListener((position, item) -> {
    // Handle navigation item selection
    switch (position) {
        case 0:
            // Navigate to Home
            break;
        case 1:
            // Navigate to Search
            break;
        case 2:
            // Navigate to Favorites
            break;
        case 3:
            // Navigate to Profile
            break;
    }
});
```

## Customization

### XML Attributes

| Attribute | Description | Default |
|-----------|-------------|---------|
| `backgroundColor` | Navigation bar background color | `#FFFFFF` |
| `selectedIconColor` | Color of selected item icon | `#2196F3` |
| `unselectedIconColor` | Color of unselected item icons | `#757575` |
| `selectedTextColor` | Color of selected item text | `#2196F3` |
| `navigationElevation` | Elevation of navigation bar | `8dp` |

### Programmatic Selection

```java
// Set selected item programmatically
bubbleNavigation.setSelectedItem(2); // Select third item (index 2)

// Get current selected position
int position = bubbleNavigation.getSelectedItemPosition();
```

## Sample App

Check out the `app` module for a complete sample implementation.

## Requirements

- Android SDK 21+
- AndroidX

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

```
MIT License

Copyright (c) 2025 mkakadiya47

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Author

**mkakadiya47**
- GitHub: [@mkakadiya47](https://github.com/mkakadiya47)

## Changelog

### Version 1.0.0
- Initial release
- Bubble navigation with smooth animations
- Customizable colors and elevation
- Support for icons and labels

---

If you like this library, please ⭐ star the repository!
