import request from '../config/request'

const findAll = () => {
    return request.get(`/class`)
}

const classService = {
    findAll
}

export default classService