import { Field, Form, Formik } from "formik";
import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import studentService from "../service/studentService";
import classService from "../service/classService";
import Swal from "sweetalert2";
import { Link } from "react-router-dom";

function Student() {
  const [students, setStudents] = useState([]);
  const [classList, setClassList] = useState([]);
  const [studentFilter, setStudentFilter] = useState({
    classId: "",
    page: 0,
  });
  const [top5List, setTop5List] = useState([]);
  const [pageCount, setPageCount] = useState(0);
  const [student, setStudent] = useState();
  const [studentUnrewardedList, setStudentUnrewardedList] = useState([]);

  const handlePageClick = (e) => {
    setStudentFilter((prev) => ({ ...prev, page: e.selected }));
  };

  const getStudentDetail = async (id) => {
    const studentResponse = await studentService.findById(id);
    setStudent(studentResponse.data);
  };

  const getStudentUnrewardedList = async () => {
    const studentUnrewardedListResponse = await studentService.findUnrewarded();
    setStudentUnrewardedList(studentUnrewardedListResponse.data);
  };

  const handleUpdateSuccess = async () => {
    try {
      await studentService.updateExpressionStatus(student);
      Swal.fire({
        icon: "success",
        title: "Sửa đổi trạng thái thành công",
        showConfirmButton: false,
        timer: 1500,
      });
    } catch (error) {
      console.warn(error);
      Swal.fire({
        icon: "error",
        title: "Sửa đổi thât bại",
        showConfirmButton: false,
        timer: 1500,
      });
    }
  };

  useEffect(() => {
    const getClassList = async () => {
      const classListResponse = await classService.findAll();
      setClassList(classListResponse.data);
    };
    getClassList();
  }, []);

  useEffect(() => {
    const getStudents = async () => {
      try {
        const studentsResponse = await studentService.findAll(studentFilter);
        setStudents(studentsResponse.data.content);
        setPageCount(studentsResponse.data.totalPages);
      } catch (error) {
        Swal.fire({
          icon: "error",
          title: "Lớp không tồn tại",
          showConfirmButton: false,
          timer: 1500,
        });
        console.warn(error);
      }
    };
    getStudents();
  }, [studentFilter]);

  useEffect(() => {
    const getTop5List = async () => {
      const top5ListResponse = await studentService.findTop5();
      setTop5List(top5ListResponse.data);
    };
    getTop5List();
  }, []);

  return (
    <>
      <Formik
        initialValues={{
          classId: "",
        }}
        onSubmit={(values) => {
          const flag = false;
          for (let classItem of classList) {
            if (classItem.name === values.classId) {
              let newValues = { ...values, classId: classItem.id };
              setStudentFilter((prev) => {
                return { ...prev, ...newValues, page: 0 };
              });
              flag = true;
            }
          }
          if (!flag) {
            setStudentFilter((prev) => {
              return { ...prev, ...values, page: 0 };
            });
          }
        }}
      >
        <Form className="d-flex justify-content-end gap-1 mt-3">
          <Field type="text" className="input_field" name="classId" />
          <button type="submit" className="btn btn-outline-primary me-2">
            Tìm kiếm
          </button>
        </Form>
      </Formik>
      <div className="d-flex justify-content-around mt-3">
        <Link to={"/create"} type="button" className="btn btn-outline-success">
          Thêm mới học sinh
        </Link>

        <button
          type="button"
          className="btn btn-outline-primary"
          data-bs-toggle="modal"
          data-bs-target="#top5"
        >
          TOP bị phạt
        </button>

        <button
          type="button"
          className="btn btn-outline-primary"
          data-bs-toggle="modal"
          data-bs-target="#unrewarded"
          onClick={getStudentUnrewardedList}
        >
          Chưa từng được thưởng
        </button>
      </div>
      <div className="table-responsive p-0 mt-3">
        <table className="table table-striped">
          <thead>
            <tr>
              <th>Tên học viên</th>
              <th>Ngày sinh</th>
              <th>Email</th>
              <th>CCCD</th>
              <th>Tên lớp</th>
              <th>Chi tiết</th>
            </tr>
          </thead>
          <tbody>
            {students.map((student, index) => (
              <tr key={index}>
                <td>{student.name}</td>
                <td>{student.dateOfBirth}</td>
                <td>{student.email}</td>
                <td>{student.identityNumber}</td>
                <td>{student.clazzDTO.name}</td>
                <td>
                  <button
                    type="button"
                    className="btn btn-outline-primary"
                    data-bs-toggle="modal"
                    data-bs-target="#detail"
                    onClick={() => getStudentDetail(student.id)}
                  >
                    Chi tiết
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        <div className="d-grid">
          <ReactPaginate
            breakLabel="..."
            nextLabel="Sau"
            onPageChange={handlePageClick}
            pageCount={pageCount}
            pageRangeDisplayed={2}
            marginPagesDisplayed={1}
            previousLabel="Trước"
            containerClassName={"pagination"}
            pageClassName="page-item"
            pageLinkClassName="page-link"
            nextClassName="page-item"
            nextLinkClassName="page-link"
            previousClassName="page-item"
            previousLinkClassName="page-link"
            activeClassName="active"
            disabledClassName="d-none"
          />
        </div>
      </div>

      {/* Modal detail */}
      <div
        className="modal fade"
        id="detail"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="detail">
                Chi tiết thông tin học viên
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              >
                <i className="bi bi-x-lg text-white" />
              </button>
            </div>
            <div className="modal-body">
              <div className="modal-body">
                <table>
                  <tr>
                    <th>Tên học viên</th>
                    <td>{student?.name}</td>
                  </tr>
                  <tr>
                    <th>Hình thức</th>
                    <td>{student?.expressionDTO.name}</td>
                  </tr>
                  <tr>
                    <th>Lý do</th>
                    <td>{student?.expressionDTO.reason}</td>
                  </tr>
                  <tr>
                    <th>Ngày xẩy ra</th>
                    <td>{student?.expressionDTO.beginDate}</td>
                  </tr>
                  <tr>
                    <th>Trạng thái</th>
                    <td>{student?.expressionDTO.expressionStatusDTO.name}</td>
                  </tr>
                  <tr>
                    {student?.expressionDTO.expressionStatusDTO.name !==
                    "Hoàn Thành" ? (
                      <button
                        className="btn btn-outline-secondary mt-3"
                        onClick={handleUpdateSuccess}
                        data-bs-dismiss="modal"
                      >
                        Hoàn Thành
                      </button>
                    ) : (
                      <></>
                    )}
                  </tr>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Modal top 5 punished */}
      <div
        className="modal fade"
        id="top5"
        tabIndex={-1}
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                TOP 5 học viên bị phạt nhiều nhất
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              >
                <i className="bi bi-x-lg text-white" />
              </button>
            </div>
            <div className="modal-body">
              <div className="modal-body">
                <div className="mb-3">
                  <div className="table-responsive p-0">
                    <table className="table">
                      <thead>
                        <tr>
                          <th>STT</th>
                          <th>Tên học viên</th>
                          <th>Tên lớp</th>
                        </tr>
                      </thead>
                      <tbody>
                        {top5List.map((student, index) => (
                          <tr key={index}>
                            <td>{++index}</td>
                            <td>{student.name}</td>
                            <td>{student.clazzDTO.name}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Modal unrewarded list */}
      <div className="modal fade" id="unrewarded">
        <div className="modal-dialog">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Danh sách các học viên chưa từng được thưởng
              </h5>
              <button
                type="button"
                className="btn-close"
                data-bs-dismiss="modal"
                aria-label="Close"
              >
                <i className="bi bi-x-lg text-white" />
              </button>
            </div>
            <div className="modal-body">
              <div className="modal-body">
                <div className="mb-3">
                  <div className="table-responsive p-0">
                    <table className="table">
                      <thead>
                        <tr>
                          <th>STT</th>
                          <th>Tên học viên</th>
                          <th>Tên lớp</th>
                        </tr>
                      </thead>
                      <tbody>
                        {studentUnrewardedList.map((student, index) => (
                          <tr key={index}>
                            <td>{++index}</td>
                            <td>{student.name}</td>
                            <td>{student.clazzDTO.name}</td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Student;
