# Animation 相关操作

```java
RAnim.get(findViewById<View>(R.id.view2_1))
            .alpha(0.2f, 0.8f)
            .reverse()
            .duration(2000)
            .animationEnd {
                Toast.makeText(
                    this@MainActivity,
                    "animation end:${System.currentTimeMillis()}",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            .fillAfter(true)
            .start()

```

```java
 RAnim.get(findViewById<View>(R.id.view2_2))
          .rotate(0f, 45f)
          .startOffset(1000)
          .interpolator(AccelerateInterpolator())
          .fillAfter(true)
          .start(2000)
```

```java

RAnim.get(findViewById<View>(R.id.view2_3))
    .rotate(0f, 45f)
    .startOffset(1000)
    .set()
    .reset()
    .alpha(0.2f, 0.8f)
    .set()
    .animationEnd {
        Log.e("angcyo", "test3....")
    }
    .start(2000)
```

```java

RAnim.get(findViewById<View>(R.id.view2_4))
    .scaleX(0.2f, 1f)
    .duration(1000)
    .set()
    .reset()
    .scaleY(0.2f, 1f)
    .startOffset(2000)
    .duration(1000)
    .set()
    .start(4000)
```

```java
RAnim.get(findViewById<View>(R.id.view2_5))
            .translateHorizontal(0f, -1f)
            .duration(1000)
            .set()
            .reset()
            .translateVertical(0f, -1f)
            .startOffset(1000)
            .duration(1000)
            .set()
            .reset()
            .rotate(0f, 360f)
            .duration(1000)
            .set()
            .reset()
            .translateHorizontal(0f, 1f)
            .startOffset(2000)
            .duration(1000)
            .set()
            .reset()
            .scale(1f, 0.2f)
            .duration(1000)
            .set()
            .reset()
            .translateVertical(0f, 1f)
            .startOffset(3000)
            .duration(1000)
            .set()
            .reset()
            .scale(0.2f, 1f)
            .duration(1000)
            .set()
            .reset()
            .animationEnd {
                Log.e("angcyo", "animation end:${System.currentTimeMillis()}")
            }
            .start(4000)

```


