/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const chartsRouter = {
  path: '/statistical',
  component: Layout,
  redirect: '/statistical/statisticalMess',
  name: 'Statistical',
  meta: { title: '统计报表', icon: 'statistical' },
  children: [
    {
      path: 'statisticalMess',
      component: () => import('@/views/statistical/index'),
      name: 'StatisticalMess',
      meta: { title: '销售扇形图统计', icon: 'statisticalMessage' }
    },
    {
      path: 'mix-chart',
      component: () => import('@/views/statistical/mix-chart'),
      name: 'MixChart',
      meta: { title: '销售与分类统计', noCache: true, icon: 'statisticalBook' }
    }
  ]
}

export default chartsRouter
