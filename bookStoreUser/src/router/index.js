import Vue from 'vue'
import Router from 'vue-router'

import Main from 'components/b-main/Main'

import classify from 'components/b-classify/b-classify'

import shopcar from 'components/b-shopcar/shopcar'
import payorder from 'components/b-shopcar/payOrder'
import chooseReceive from 'components/b-shopcar/chooseReceive'
import logistics from 'components/b-shopcar/logistics'
import comment from 'components/b-shopcar/comment'

import myCenter from 'components/b-mycenter/myCenter'
import persondData from 'components/b-mycenter/personData'
import changePass from 'components/b-mycenter/changePass'
import receiveAddress from 'components/b-mycenter/receiveAddress'
import collect from 'components/b-mycenter/collect'
import myCoupon from 'components/b-mycenter/myCoupon'
import aboutStore from 'components/b-mycenter/aboutStore'
import orderStatu from 'components/b-mycenter/orderStatu'

import chat from 'components/b-chat/chat'

import login from 'components/b-login/login'
import register from 'components/b-login/register'

import comInterface from 'components/b-comInterface/com_Interface'
import comlist from 'components/b-comInterface/com_list'
import allCom from 'components/b-comInterface/b-comment/allComment'
import picCom from 'components/b-comInterface/b-comment/havePicComment'
import noPicCom from 'components/b-comInterface/b-comment/noPicComment'

import firstPage from 'components/b-firstpage/firstPage'
import search from 'components/b-firstpage/search'
import sort from 'components/b-firstpage/sort'
import showClickSort from 'components/b-firstpage/showClickSort'
import volume from 'components/b-firstpage/volume'
import topic from 'components/b-firstpage/topic'
import topicMess from 'components/b-firstpage/topicMess'
import message from 'components/b-firstpage/message'

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {
      // name:'Main',
      path:'/',
      component:Main,
      children:[
        {
          path: '/',
          name: 'firstPage',
          component:firstPage
        },
        {//首页
          path: 'firstPage',
          name: 'firstPage1',
          component:firstPage
        },
        {//分类
          path: 'classify',
          name: 'classify',
          component:classify
        },
        {//购物车
          path: 'shopcar',
          name: 'shopcar',
          component:shopcar
        },
        {//个人中心,未登录界面
          path: 'myCenter',
          name: 'myCenter',
          component:myCenter,
          meta:{
            navShow:true
          }
        },
      ]
    },

    {//登陆
      path: '/login',
      name: 'login',
      component:login
    },
    {//注册
      path: '/register',
      name: 'register',
      component:register
    },

    {//查询界面
      path:'/firstPage/search',
      name:'search',
      component:search
    },
    {//排行榜
      path:'/firstPage/sort',
      name:'sort',
      component:sort
    },
    {//进入排行榜
      path:'/firstPage/sort/showClickSort',
      name:'showClickSort',
      component:showClickSort
    },
    {//优惠卷
      path:'/firstPage/volume',
      name:'volume',
      component:volume
    },
    {//专题
      path:'/firstPage/topic',
      name:'topic',
      component:topic
    },
    {//专题信息
      path:'/firstPage/topic/topicMess',
      name:'topicMess',
      component:topicMess
    },
    {//专题
      path:'/firstPage/message',
      name:'message',
      component:message
    },

    {//确认订单界面
      path:'/shopcar/payorder',
      name:'payorder',
      component:payorder
    },
    {//选择联系人页面
      path:'/shopcar/chooseReceive',
      name:'chooseReceive',
      component:chooseReceive
    },
    {//物流页面
      path:'/shopcar/logistics',
      name:'logistics',
      component:logistics
    },
    {//评论页面
      path:'/shopcar/comment',
      name:'comment',
      component:comment
    },


    {//修改密码
      path: '/myCenter/changePass',
      name: 'changePass',
      component:changePass
    },
    {//修改个人资料
      path:'/myCenter/personData',
      name:'personData',
      component: persondData,
    },
    {//收货地址
      path:'/myCenter/receiveAddress',
      name:'receiveAddress',
      component:receiveAddress
    },
    {//收藏夹
      path:'/myCenter/collect',
      name:'collect',
      component:collect
    },
    {//我的优惠卷
      path:'/myCenter/myCoupon',
      name:'myCoupon',
      component:myCoupon
    },
    {//客服
      path:'/myCenter/chat',
      name:'chat',
      component:chat
    },
    {//关于书店
      path:'/myCenter/aboutStore',
      name:'aboutStore',
      component:aboutStore
    },
    {//订单
      path:'/myCenter/orderStatu',
      name:'orderStatu',
      component:orderStatu
    },

    {//商品详情界面
      path: '/comInterface',
      name: 'comInterface',
      component:comInterface,
    },
    {//全部评价
      path: '/comInterface/comlist',
      name: 'comlist',
      component: comlist,
      children:[
        {
          path:'/',
          name:'allCom',
          component:allCom
        },
        {
          path:'/allCom',
          name:'allCom1',
          component:allCom
        },
        {
          path:'/picCom',
          name:'picCom',
          component:picCom
        },
        {
          path:'/noPicCom',
          name:'noPicCom',
          component:noPicCom
        },
      ]
    },

  ]
})
