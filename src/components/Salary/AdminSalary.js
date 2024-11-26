import React, { useState } from 'react';
import { Trash, NotePencil } from 'phosphor-react';
import AdminSalaryModal from './AdminSalaryModal';

const AdminSalary = () => {
    const [showModal, setShowModal] = useState(false);
    const [selectedRecord, setSelectedRecord] = useState(null);
    
    const handleModalOpen = (record) => {
        setSelectedRecord(record);
        setShowModal(true);
        console.log("Opening Modal:", record);


        console.log("pass open")
    };
    
    const handleModalClose = () => {
        setShowModal(false);
        setSelectedRecord(null);
        console.log("Closing Modal");


        console.log("pass closing")
    };
    


    const handleSave = (updatedRecord) => {
        setSalary((prevSalary) =>
            prevSalary.map((loan) => (loan.id === updatedRecord.id ? updatedRecord : loan))
        );

        console.log("pass save")
    };
    
    const handleDelete = (emp_code) => {
        setSalary(prevSalary => prevSalary.filter(record => record.emp_code !== emp_code));

        console.log("pass delete")
    };

    const [Salary, setSalary] = useState([
        {
            emp_code: 'LP-0101',
            id: 1,
            month: 1,
            year: 2022,
            bonus: 500,
            penalty: 100,
            real_pay: 3900,
            full_work: 22,
            half_work: 0,
            absence: 1,
            name: 'Joan Dyer',
            image: 'https://randomuser.me/api/portraits/men/1.jpg',
        },

        {
            emp_code: 'LP-2007',
            id: 737,
            month: 1,
            year: 2022,
            bonus: 500,
            penalty: 100,
            real_pay: 3900,
            full_work: 22,
            half_work: 0,
            absence: 1,
            name: 'Ly Quang',
            image: 'https://randomuser.me/api/portraits/men/1.jpg',
        },
    ]);

    const SalaryRecordList = ({ Salary }) => (
        <tbody>
            {Salary.map((record, index) => (
                <tr key={index}>
                    <td>{record.emp_code}</td>
                    <td className="d-flex align-items-center">
                        <img className="avatar rounded-circle me-2" src={record.image} alt="" />
                        <span className="fw-bold">{record.name}</span>
                    </td>
                    <td>{record.month}</td>
                    <td>{record.year}</td>
                    <td>{record.full_work}</td>
                    <td>{record.half_work}</td>
                    <td>{record.absence}</td>
                    <td><span className="text-success fw-bold">{record.bonus}Đ</span></td>
                    <td><span className="text-danger fw-bold">{record.penalty}Đ</span></td>
                    <td><span className="fw-bold">{record.real_pay}Đ</span></td>
                    <td>
                        <div className="btn-group" role="group" aria-label="Basic outlined example">
                            <button type="button" className="btn btn-outline-secondary" onClick={() => handleModalOpen(record)}>
                                <NotePencil size={20} className="text-success" />
                            </button>
                            <button type="button" className="btn btn-outline-secondary deleterow" onClick={() => handleDelete(record.emp_code)}>
                                <Trash size={20} className="text-danger" />
                            </button>
                        </div>
                    </td>
                </tr>
            ))}
        </tbody>
    );

    return (
        <div className="body d-flex py-lg-3 py-md-2">
            <div className="container-xxl">
                <div className="row align-items-center">
                    <div className="border-0 mb-4">
                        <div className="card-header py-3 no-bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap">
                            <h3 className="fw-bold mb-0">Bảng lương nhân viên</h3>
                        </div>
                    </div>
                </div>
                <div className="row clearfix g-3" style={{ width: '80vw' }}>
                    <div className="col-sm-12">
                        <div className="card mb-3">
                            <div className="card-body" style={{ overflowY: 'auto', height: '100%' }}>
                                <table className="table table-hover align-middle mb-0">
                                    <thead>
                                        <tr>
                                            <th>Mã nhân viên</th>
                                            <th>Tên nhân viên</th>
                                            <th>Tháng</th>
                                            <th>Năm</th>
                                            <th>Số ngày đủ ca</th>
                                            <th>Số ngày nửa ca</th>
                                            <th>Số ngày vắng</th>
                                            <th>Thưởng</th>
                                            <th>Phạt</th>
                                            <th>Lương thực tế</th>
                                            <th>Hoạt động</th>
                                        </tr>
                                    </thead>
                                    <SalaryRecordList Salary={Salary} />
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <AdminSalaryModal
                show={showModal}
                handleClose={handleModalClose}
                record={selectedRecord}
                handleSave={handleSave}
            />
        </div>
    );
};

export default AdminSalary;
