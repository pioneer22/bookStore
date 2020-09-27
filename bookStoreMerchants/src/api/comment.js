
import ajax from './ajax'

export const reqComment = () => ajax('/api/comment/reqComment')

export const replyComment = (comId, comReply) => ajax('/api/comment/replyComment', { comId, comReply }, 'POST')

export const searchBook = (searchKey) => ajax('/api/comment/searchBook', { searchKey }, 'POST')
