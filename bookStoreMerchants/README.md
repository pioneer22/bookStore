# vue-admin-bookstore
这是一个网上书店商家端，这里使用了element-Ui来实现页面。
主要功能包括
1.图书的上下架，图书信息修改等。
2.订单的处理。
3.消息管理，主要是公告信息的处理，以及收到未读的聊天信息。
4.评论管理，对评论进行查看和回复。
5.客服管理，查看聊天信息，发起聊天等，这里使用了WebSocket实现实时聊天。
6.优惠管理，主要是优惠卷的发放和删除。
7.专题管理，实现专题的发布，添加专题图书等。
8.excel,信息的导入导出，使用了xlsx实现图书一键上架以及订单信息的导出。
9.统计报表，对数据进行统计，使用eCharts使数据更直观。
## Build Setup

```bash
# Clone project
git clone https://github.com/pioneer22/bookStoreMerchants.git

# Install dependencies
npm install

# Serve with hot reload at localhost:9528
npm run dev

# Build for production with minification
npm run build

# Build for production and view the bundle analyzer report
npm run build --report
```

## Demo
<p align="center">
    <img src="https://s2.ax1x.com/2019/05/12/Eh7NnA.png" alt="Eh7NnA.png" border="0">
</p>
<p align="center">
    <img src="https://s2.ax1x.com/2019/05/12/Eh7G1e.png" alt="Eh7G1e.png" border="0">
</p>
<p align="center">
    <img src="https://s2.ax1x.com/2019/05/12/Eh7YXd.png" alt="Eh7YXd.png" border="0">
</p>
<p align="center">
    <img src="https://s2.ax1x.com/2019/05/12/Eh71fO.png" alt="Eh71fO.png" border="0">
</p>
<p align="center">
    <img src="https://s2.ax1x.com/2019/05/12/Eh78pD.png" alt="Eh78pD.png" border="0">
</p>
<p align="center">
    <img src="https://s2.ax1x.com/2019/05/12/Eh7J6H.png" alt="Eh7J6H.png" border="0">
</p>


### Element-Ui using cdn tutorial

First find `index.html`([root directory](https://github.com/PanJiaChen/vue-admin-template/blob/element-ui-cdn/index.html))

Import css and js of `Element`, and then import vue. Because `Element` is vue-dependent, vue must be import before it.

Then find [webpack.base.conf.js](https://github.com/PanJiaChen/vue-admin-template/blob/element-ui-cdn/build/webpack.base.conf.js)
Add `externals` to make webpack not package vue and element.

```
externals: {
  vue: 'Vue',
  'element-ui':'ELEMENT'
}
```

Finally there is a small detail to pay attention to that if you import vue in global, you don't need to manually `Vue.use(Vuex)`, it will be automatically mounted, see
[issue](https://github.com/vuejs/vuex/issues/731)

And you can use `npm run build --report` to see the effect

Pictured:
![demo](https://panjiachen.github.io/images/element-cdn.png)

**[Detailed code](https://github.com/PanJiaChen/vue-admin-template/commit/746aff560932704ae821f82f10b8b2a9681d5177)**

**[Branch](https://github.com/PanJiaChen/vue-admin-template/tree/element-ui-cdn)**

## Browsers support

Modern browsers and Internet Explorer 10+.

| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="IE / Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE / Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |
| --------- | --------- | --------- | --------- |
| IE10, IE11, Edge| last 2 versions| last 2 versions| last 2 versions

