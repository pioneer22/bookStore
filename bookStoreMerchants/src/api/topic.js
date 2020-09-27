import ajax from './ajax'

export const reqTopic = () => ajax('/api/topic/reqTopic')

export const deleteTopic = (topicId) => ajax('/api/topic/deleteTopic', { topicId })

export const deleteTopicBook = (topicId, bookId) => ajax('/api/topic/deleteTopicBook', { topicId, bookId }, 'POST')

export const searchTopic = (searchKey) => ajax('/api/topic/searchTopic', { searchKey }, 'POST')

export const reqBook = () => ajax('/api/topic/reqBook')

export const insertTopicBook = (topicId, books) => ajax('/api/topic/insertTopicBook', { topicId, books }, 'POST')

export const insertTopic = (topicImg, topicName, books) => ajax('/api/topic/insertTopic', { topicImg, topicName, books }, 'POST')
