# RDrawable
使用代码的方式,创建Drawable

![](https://raw.githubusercontent.com/angcyo/RDrawable/master/art/png3.png)

---

```java
RDrawable.get(this)
            .strokeWidth(10) //边框10px
            .strokeColor(Color.YELLOW) //边框颜色
            .solidColor(Color.RED) //填充颜色
            .cornerRadiiLeft(10f) //左边2个角的圆角
            .doIt() //获取标准的Drawable
```

```java
RDrawable.get(this)
            .solidColor(Color.RED) //填充色
            .cornerRadius(20f) //4个角的圆角
            .pressed(true) //设置为 按下 的状态
            .andSelected(true) //同样设置为 选中 的状态
            .andChecked(true) //同样设置为 勾选 的状态
            .reset() //清空值
            .stroke(4, Color.BLACK) //边框大小和颜色
            .normal() //设置为 正常 的状态
            .state() //获取 具有状态的 Drawable
```

```java
RDrawable.get(this)
            .solidColor(Color.MAGENTA) //同上 
            .cornerRadius(20f) //同上
            .andRippleContent() //作为 ripple 的 content
            .rippleColor(Color.WHITE) //ripple的颜色
            .ripple() //获取ripple drawable
```

```java
RDrawable.get(this)
            .circle(Color.RED) //红色的圆
            .doIt()
```

```java
RDrawable.get(this)
            .circle(Color.RED)
            .addLayer()
            .lineColor(Color.GREEN)
            .rightLine(10)
            .addLayer()
            .get()
```

```java
RDrawable.get(applicationContext)
            .shape(GradientDrawable.OVAL)
            .gradientType(GradientDrawable.SWEEP_GRADIENT)
            .gradientOrientation(GradientDrawable.Orientation.TOP_BOTTOM)
            .gradientColors(intArrayOf("#004286FF".toColor(), "#224286FF".toColor(), "#884286FF".toColor()))
            .addLayer()
            .reset()
            .shape(GradientDrawable.OVAL)
            .solidColor("#224286FF".toColor())
            .addLayer()
            .layerInset(20)
            .solidColor("#444286FF".toColor())
            .addLayer()
            .layerInset(40)
            .solidColor("#664286FF".toColor())
            .addLayer()
            .andReset()
            .andRotate(60f)
            .andReset()
//            .andReset()
//            .clipGravity(Gravity.LEFT)
//            .andWrapper()
            .inset(30)
            .andWrapper()
            .andReset()
            .clipWidth(0.4f)
            //.andRotate(60f)
            .wrapper()
```

---

[更多属性](https://github.com/angcyo/RDrawable/blob/master/app/src/main/java/com/angcyo/drawable/RDrawable.java)


### 注意:

`RotateDrawable` 属性的相关API都是 `added in API level 21` 所以低版本不支持 `rotate` 操作.

`addLayer` `added in API level 23` 所以 低版本不支持为 每一个Drawable 设置 `layerInset` .

兼容代码如下:
```
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int layer = layerDrawable.addLayer(drawable);
            layerDrawable.setLayerInset(layer, layerInsetLeft,
                    layerInsetTop,
                    layerInsetRight,
                    layerInsetBottom);
        } else {
            Drawable[] old = this.drawables;
            int oldSize = 0;
            if (old != null) {
                oldSize = old.length;
            }
            Drawable[] news = new Drawable[oldSize + 1];
            if (old != null) {
                System.arraycopy(old, 0, news, 0, oldSize);
            }
            news[oldSize] = drawable;

            this.drawables = news;

            //低版本不支持单独为每一个drawable设置 layerInset
            layerDrawable = new LayerDrawable(news);
            for (int i = 0; i < news.length; i++) {
                layerDrawable.setLayerInset(i, layerInsetLeft,
                        layerInsetTop,
                        layerInsetRight,
                        layerInsetBottom);
            }
        }
```

---
**群内有`各(pian)种(ni)各(jin)样(qun)`的大佬,等你来撩.**

# 联系作者
[点此快速加群](https://shang.qq.com/wpa/qunwpa?idkey=cbcf9a42faf2fe730b51004d33ac70863617e6999fce7daf43231f3cf2997460)

> 请使用QQ扫码加群, 小伙伴们都在等着你哦!

![](https://raw.githubusercontent.com/angcyo/res/master/image/qq/qq_group_code.png)

> 关注我的公众号, 每天都能一起玩耍哦!

![](https://raw.githubusercontent.com/angcyo/res/master/image/weixin/%E8%AE%A2%E9%98%85%E5%8F%B7_%E4%BA%8C%E7%BB%B4%E7%A0%81/qrcode_for_gh_59fa6d9a51d8_258_8cm.jpg)

 
  
