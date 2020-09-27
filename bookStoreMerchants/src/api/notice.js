
import ajax from './ajax'

export const getMorningPaper = () => ajax('/api/notice/getMorningPaper')

export const sendNotice = (noticeMess, businessId) => ajax('/api/notice/sendNotice', { noticeMess, businessId }, 'POST')

export const deleteNotice = (paperId) => ajax('/api/notice/deleteNotice', { paperId })

export const deleteAllNotice = (businessId) => ajax('/api/notice/deleteAllNotice', { businessId })

export const reqChatMess = (businessId) => ajax('/api/notice/reqChatMess', { businessId })

export const deleteChat = (chatId) => ajax('/api/notice/deleteOneChat', { chatId })

export const searchChat = (businessId, searchKey) => ajax('/api/notice/searchChat', { businessId, searchKey }, 'POST')

export const deleteChooseChat = (chatIdMess) => ajax('/api/notice/deleteChooseChat', { chatIdMess }, 'POST')

export const reqUser = () => ajax('/api/notice/reqUser')

export const reqChat = (businessId, userId) => ajax('/api/notice/reqChat', { businessId, userId }, 'POST')

export const saveMess = (businessId, userId, context) => ajax('/api/notice/saveMess', { businessId, userId, context }, 'POST')

export const saveMessAll = (businessId, context) => ajax('/api/notice/saveMessAll', { businessId, context }, 'POST')

export const searchUser = (searchKey) => ajax('/api/notice/searchUser', { searchKey }, 'POST')

export const reqNoReadCount = (businessId) => ajax('/api/notice/reqNoReadCount', { businessId })

export const reqNoReadMess = (businessId) => ajax('/api/notice/reqNoReadMess', { businessId })

export const turnRead = (Id) => ajax('/api/notice/turnRead', { Id })

export const turnKnow = (Id) => ajax('/api/notice/turnKnow', { Id })
