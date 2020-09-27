
import ajax from './ajax'

export const getOrderList = () => ajax('/api/article/exportSelectExcel')

export const getBookList = () => ajax('/api/article/exportExcel')

export const uploadBook = (books) => ajax('/api/article/importExcel', { books }, 'POST')

import request from '@/utils/request'

export function fetchArticle(id) {
  return request({
    url: '/article/detail',
    method: 'get',
    params: { id }
  })
}

export function fetchPv(pv) {
  return request({
    url: '/article/pv',
    method: 'get',
    params: { pv }
  })
}

export function createArticle(data) {
  return request({
    url: '/article/create',
    method: 'post',
    data
  })
}

export function updateArticle(data) {
  return request({
    url: '/article/update',
    method: 'post',
    data
  })
}

