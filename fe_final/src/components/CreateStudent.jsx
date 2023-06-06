import React, { useState, useEffect } from "react";
import studentService from "../service/studentService";
import classService from "../service/classService";
import { ErrorMessage, Field, Form, Formik } from "formik";
import Swal from "sweetalert2";
import { Link, useNavigate } from "react-router-dom";
import * as Yup from "yup"

function CreateStudent() {
  const [classList, setClassList] = useState([]);
  let navigate = useNavigate();

  useEffect(() => {
    const getClassList = async () => {
      const classListResponse = await classService.findAll();
      setClassList(classListResponse.data);
    };
    getClassList();
  }, []);
  return (
    <>
      <Formik
        initialValues={{
          name: "",
          dateOfBirth: "",
          email: "",
          identityNumber: "",
          clazzDTO: "",
        }}
        validationSchema={Yup.object({
            name: Yup.string().required("Không được để trống"),
            dateOfBirth: Yup.string().required("Không được để trống"),
            email: Yup.string().required("Không được để trống").email("Sai format email"),
            identityNumber: Yup.string().required("Không được để trống"),
            clazzDTO: Yup.string().required("Không được để trống"),
        })}
        onSubmit={async (values) => {
          const newValues = {
            ...values,
            clazzDTO: { id: +values.clazzDTO },
          };
          try {
            await studentService.create(newValues);
            Swal.fire({
              icon: "success",
              title: "Thêm mới thành công",
              showConfirmButton: false,
              timer: 1500,
            });
            document.getElementById("email-error").innerHTML = ""
            navigate("/");
          } catch (error) {
            console.warn(error);
            Swal.fire({
              icon: "error",
              title: "Thêm mới thât bại",
              showConfirmButton: false,
              timer: 1500,
            });
            if (error.response.data === "Email đã tồn tại") {
                document.getElementById("email-error").innerHTML = "Email đã tồn tại"
            }
          }
        }}
      >
        <Form>
          <div className="container testbox" style={{ marginTop: "70px" }}>
            <div className="">
              <h1>Thêm mới học viên</h1>
              <div className="item mb-3">
                <label htmlFor="name" style={{ width: "150px" }}>
                  Tên học viên:{" "}
                </label>
                <Field type="text" name="name" placeholder="" id="name" />
                <ErrorMessage
                  name="name"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="item mb-3">
                <label htmlFor="email" style={{ width: "150px" }}>
                  {" "}
                  Email:{" "}
                </label>
                <Field type="text" name="email" id="email" />
                <ErrorMessage
                  name="email"
                  component="div"
                  className="text-danger"
                />
                <div id="email-error" className="text-danger"></div>
              </div>
              <div className="item mb-3">
                <label htmlFor="dateOfBirth" style={{ width: "150px" }}>
                  {" "}
                  Ngày sinh:{" "}
                </label>
                <Field
                  type="date"
                  name="dateOfBirth"
                  id="dateOfBirth"
                  style={{ width: "188px", height: "29px" }}
                />
                <ErrorMessage
                  name="dateOfBirth"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="item mb-3">
                <label htmlFor="identityNumber" style={{ width: "150px" }}>
                  CCCD:{" "}
                </label>
                <Field type="text" name="identityNumber" id="identityNumber" />
                <ErrorMessage
                  name="identityNumber"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="item mb-3">
                <label htmlFor="clazzDTO" style={{ width: "150px" }}>
                  Lớp:{" "}
                </label>
                <Field
                  as="select"
                  name="clazzDTO"
                  id="identityNumber"
                  style={{ width: "188px", height: "29px" }}
                >
                  <option value="">--- Hãy chọn lớp ---</option>
                  {classList.map((classItem, index) => (
                    <option value={classItem.id} key={index}>
                      {classItem.name}
                    </option>
                  ))}
                </Field>
                <ErrorMessage
                  name="identityNumber"
                  component="div"
                  className="text-danger"
                />
              </div>
              <div className="btn-block">
                <button type="submit" className="btn btn-success me-3">
                  Thêm mới
                </button>
                <Link to={"/"} type="button" className="btn btn-secondary">
                  Thoát
                </Link>
              </div>
            </div>
          </div>
        </Form>
      </Formik>
    </>
  );
}

export default CreateStudent;
