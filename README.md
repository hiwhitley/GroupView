#GroupView
##一、前言
最近做东西的时候要用到qq讨论组群头像的功能，网上找了下，发现别人的并不是特别的好用，效果并不是和qq这个很像，或者是使用gridView，（[NineGridImageView](https://github.com/laobie/NineGridImageView)）这样比较麻烦，所以就自己想了一个。

![这里写图片描述](http://img.blog.csdn.net/20160321192159442)

##二、效果展示
直接上效果图：

![这里写图片描述](http://img.blog.csdn.net/20160321192652699)

是不是，还是很像的啊，o(∩_∩)o 
从图里应该就可以看的出来这个有四个个功能：

type：圆或者方
padding：图片间距
cornor：设置圆角矩形
backgroundGp：背景颜色

##三、使用方法
使用也是超级简单。
**布局文件：**

```
   <com.hiwhitley.groupview.GroupView
            android:id="@+id/gv_face7"
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_marginTop="10dp"
            app:backgroundGP="#1FEAE5"
            app:cornor="10dp"
            app:type="square" />
```
这里通过自定义属性设置：

type：圆或者方
padding：图片间距
cornor：设置圆角矩形
backgroundGp：背景颜色

**代码中需要：**
```
        bitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.face));
        mGroupView = (GroupView) findViewById(R.id.gv_face1);
        mGroupView.setImageBitmaps(bitmapList);
```

##四、总结：
希望这个可以帮助有需要的人，自己也要更加努力！
可以的话，给个星星鼓励下！谢谢各位了
