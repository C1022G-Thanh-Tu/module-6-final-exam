import request from '../config/request'

const findAll = ({ classId, page }) => {
    return request.get(`/student?classId=${classId}&page=${page?page:0}`)
}

const findTop5 = () => {
    return request.get(`/student/top-5`)
}

const findById = (id) => {
    return request.get(`/student/detail/${id}`)
}

const updateExpressionStatus = (student) => {
    return request.put(`/student`, {...student})
}

const findUnrewarded = () => {
    return request.get(`/student/unrewarded`)
}

const create = (student) => {
    return request.post(`/student`, {...student})
}

const studentService = {
    findAll,
    findTop5,
    findById,
    updateExpressionStatus,
    findUnrewarded,
    create
}

export default studentService