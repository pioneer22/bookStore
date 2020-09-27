import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

import chartsRouter from './modules/charts'
/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in subMenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },
  {
    path: '/bookManage',
    component: Layout,
    redirect: '/bookManage/bookDeal',
    name: 'BookManage',
    meta: { title: '图书管理', icon: 'bookManage' },
    children: [
      {
        path: 'bookDeal',
        component: () => import('@/views/bookManage/bookDeal/index'),
        name: 'BookDeal',
        meta: { title: '图书信息', icon: 'bookMessage' }
      },
      {
        path: 'bookShelf',
        component: () => import('@/views/bookManage/bookShelf/index'),
        name: 'BookShelf',
        meta: { title: '图书上架', icon: 'bookSelf' }
      },
      {
        path: 'bookClassify',
        component: () => import('@/views/bookManage/bookClassify/index'),
        name: 'BookClassify',
        meta: { title: '图书分类', icon: 'bookClassify' }
      }
    ]
  },
  {
    path: '/orderManage',
    component: Layout,
    redirect: '/orderManage/orderMess',
    name: 'OrderManage',
    meta: { title: '订单管理', icon: 'orderManage' },
    children: [
      {
        path: 'orderMess',
        component: () => import('@/views/orderManage/orderMess/index'),
        name: 'OrderMess',
        meta: { title: '订单信息', icon: 'orderMessage' }
      },
      {
        path: 'logistics',
        component: () => import('@/views/orderManage/logistics/index'),
        name: 'Logistics',
        meta: { title: '订单物流', icon: 'logistics' }
      }
    ]
  },
  {
    path: '/noticeManage',
    component: Layout,
    redirect: '/noticeManage/noticeMess',
    name: 'NoticeManage',
    alwaysShow: true, // 取消只有一行默认将子路由作为根路由显示在侧边栏中
    meta: { title: '消息管理', icon: 'noticeManage' },
    children: [
      {
        path: 'noticeMess',
        component: () => import('@/views/noticeManage/noticeMess/index'),
        name: 'NoticeMess',
        meta: { title: '早报信息', icon: 'notice' }
      },
      {
        path: 'message',
        component: () => import('@/views/noticeManage/message/index'),
        name: 'Message',
        meta: { title: '消息', icon: 'messIcon' }
      }
    ]
  },
  {
    path: '/commentManage',
    component: Layout,
    redirect: '/CommentManage/commentMess',
    name: 'CommentManage',
    alwaysShow: true,
    meta: { title: '评论管理', icon: 'commentManage' },
    children: [
      {
        path: 'commentMess',
        component: () => import('@/views/commentManage/commentMess/index'),
        name: 'CommentMess',
        meta: { title: '评论管理', icon: 'comment' }
      }
    ]
  },
  {
    path: '/chatManage',
    component: Layout,
    redirect: '/chatManage/chatMess',
    name: 'ChatManage',
    meta: { title: '客服管理', icon: 'chatManage' },
    children: [
      {
        path: 'chatMess',
        component: () => import('@/views/chatManage/chatMess/index'),
        name: 'ChatMess',
        meta: { title: '聊天记录', icon: 'chatMessage' }
      },
      {
        path: 'launchChat',
        component: () => import('@/views/chatManage/launchChat/index'),
        name: 'LaunchChat',
        meta: { title: '发起聊天', icon: 'launchChat' }
      }
    ]
  },
/*  {
    path: '/messageManage',
    component: Layout,
    redirect: '/messageManage/message',
    name: 'MessageManage',
    alwaysShow: true,
    meta: { title: '消息管理', icon: 'messManage' },
    children: [
    ]
  },*/
  {
    path: '/discountManage',
    component: Layout,
    redirect: '/discountManage/discountMess',
    name: 'DiscountManage',
    alwaysShow: true,
    meta: { title: '优惠管理', icon: 'discountManage' },
    children: [
      {
        path: 'discountMess',
        component: () => import('@/views/discountManage/discountMess/index'),
        name: 'DiscountMess',
        meta: { title: '优惠卷管理', icon: 'coupon' }
      }
    ]
  },
  {
    path: '/topic',
    component: Layout,
    redirect: '/topic/topicMess',
    name: 'Topic',
    meta: { title: '专题管理', icon: 'topic' },
    children: [
      {
        path: 'topicMess',
        component: () => import('@/views/topic/topicMess/index'),
        name: 'TopicMess',
        meta: { title: '专题信息', icon: 'topicMessage' }
      },
      {
        path: 'topicSend',
        component: () => import('@/views/topic/topicSend/index'),
        name: 'TopicSend',
        meta: { title: '专题发布', icon: 'topicSend' }
      }
    ]
  },
  {
    path: '/excel',
    component: Layout,
    redirect: '/excel/export-excel',
    name: 'Excel',
    meta: {
      title: 'excel',
      icon: 'excel'
    },
    children: [
      {
        path: 'export-excel',
        component: () => import('@/views/excel/export-excel'),
        name: 'ExportExcel',
        meta: { title: '图书导出Excel', icon: 'Export' }
      },
      {
        path: 'export-selected-excel',
        component: () => import('@/views/excel/select-excel'),
        name: 'SelectExcel',
        meta: { title: '订单导出Excel', icon: 'Export' }
      },
      {
        path: 'upload-excel',
        component: () => import('@/views/excel/upload-excel'),
        name: 'UploadExcel',
        meta: { title: '图书导入Excel', icon: 'import' }
      }
    ]
  },
  chartsRouter,
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
