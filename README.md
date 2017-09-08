## MultiColorIconView


Changing colors of drawable icons by reusing the single icon with different colors.


### Screenshots


## Pros
It increases the app performance by reusing the single icon.

Reduces the apk size.

Easy to transform color of the image through hex code.


### Add to project

Add dependencies in your /app/build.gradle file

```
      compile 'com.sutanu.multiiconview:multiiconview:1.0.1'

```

## Example

Put the MultiColorIconView in the layout xml

```
       <com.sutanu.multiiconview.MultiColorIconView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:multiColorIconViewColor="#F4A8A8"
        android:id="@+id/multi_color_icon_view"
        app:viewAnimationDuration="1000"
        app:animationType="fadeIn"
        android:src="@drawable/image"/>
```

```
       MultiColorIconView multiColorIconView = (MultiColorIconView)findViewById(R.id.multi_color_icon_view);

       multiColorIconView.setMultiColorIconViewColor(Color.RED);
        
```
### Apply animation

        multiColorIconView.setViewDuration(1000);
        multiColorIconView.setAnimationType(MultiColorIconView.FADE_IN);


If it doesn't work, please send me a email, sutanurath@gmail.com

### Limitations

Works only with plain white drawable icons.

### License

```
Copyright (C) 2017 sutanurath

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

