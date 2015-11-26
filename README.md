# MakeYourAppMaterial

This project will fit with Android Material design pattern.

I had add CoordinatorLayout and FloatActionButton.

Share function will update.

## About CoordinatorLayout

If your device platform version more than APP compile version,

the Toolbar will not scroll hide.

## About FloatActionButton

## About SnackBar


## About Application run flow

1. Enter point is ArticleListActivity
1. If ArticleListActivity start normal, refresh data with UpdateService
1. ArticleListActivity will start ArticleLoader (ID:0) to get data. This loader is a CursorLoader
1. ArticleLoader load data from Uri (ItemsContract.Items.buildDirUri())
1. ItemsContact includes ContentProvider information define. base uri is "content://com.example.xyzreader"
1. The ContentProvider implementation is "ItemsProvider" In AndroidManifest.xml 
1. ItemsProvider will query from SQLite db and register notify uri for data update.
1. UpdateService will invoke remote api endpoint to get data.
1. When click Item, will start ACTION_VIEW and uri is "content://...."
1. Android OS will find ContentProvider and get content mime-type and will search Activity which support data
   mime-type is this. Will start ArticleDetailActivity
