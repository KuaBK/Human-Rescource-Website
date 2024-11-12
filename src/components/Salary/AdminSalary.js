
import React, { useState } from 'react';
import { Trash, NotePencil } from 'phosphor-react';
import AdminSalaryModal from './AdminSalaryModal';

const AdminSalary = () => {
    const [showModal, setShowModal] = useState(false);
    const [selectedLoan, setSelectedLoan] = useState(null);

    const handleModalOpen = (loan) => {
        setSelectedLoan(loan);
        setShowModal(true);
    };

    const handleModalClose = () => {
        setShowModal(false);
        setSelectedLoan(null);
    };

    const handleSave = (updatedLoan) => {
        // Xử lý lưu thông tin cập nhật
        console.log(updatedLoan);
    };

    const handleDelete = (loanId) => {
        setLoans(prevLoans => prevLoans.filter(loan => loan.employeeId !== loanId));
    };

    const [loans, setLoans] = useState([
        {
            employeeId: 'LP-0101',
            name: 'Joan Dyer',
            image: 'https://randomuser.me/api/portraits/men/1.jpg',
            purpose: 'for weddings and family functions',
            date: '14 Jan, 2022',
            amount: 4000,
            department: 'Sales',
            workingDays: 22,
            status: 'Pending'
        },
    ]);

    const EmployeeLoanList = ({ loans }) => {
        return (
            <tbody>
                {loans.map((loan, index) => (
                    <tr key={index}>
                        <td><span className="fw-bold">{loan.employeeId}</span></td>
                        <td className="d-flex align-items-center">
                            <img className="avatar rounded-circle" src={loan.image} alt="" />
                            <div>
                                <span className="fw-bold ms-1">{loan.name}</span>
                                <span className="text-muted ms-1 d-block">Purpose : {loan.purpose}</span>
                            </div>
                        </td>
                        <td>{loan.department}</td>
                        <td>{loan.workingDays}</td>
                        <td>{loan.date}</td>
                        <td><span className="text-danger fw-bold">${loan.amount}</span></td>
                        <td><span className={`fw-bold ${loan.status === 'Paid' ? 'text-success' : 'text-warning'}`}>{loan.status}</span></td>
                        <td>
                            <div className="btn-group" role="group" aria-label="Basic outlined example">
                                <button type="button" className="btn btn-outline-secondary" onClick={() => handleModalOpen(loan)}>
                                    <NotePencil size={20} className="text-success" />
                                </button>
                                <button type="button" className="btn btn-outline-secondary deleterow" onClick={() => handleDelete(loan.employeeId)}>
                                    <Trash size={20} className="text-danger" />
                                </button>
                            </div>
                        </td>
                    </tr>
                ))}
            </tbody>
        );
    };

    return (
        <div className='body d-flex py-lg-3 py-md-2'>
            <div className='container-xxl'>
                <div className='row align-items-center'>
                    <div className='border-0 mb-4'>
                        <div className='card-header py-3 no-bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap'>
                            <h3 className='fw-bold mb-0'>Employee Salary</h3>
                        </div>
                    </div>
                </div>
                <div className="row clearfix g-3" style={{ width: '80vw' }}>
                    <div className="col-sm-12" style={{ width: '100%' }}>
                        <div className="card mb-3" style={{ width: '100%' }}>
                            <div className="card-body" style={{ overflowY: 'auto', height: '100%' }}>
                                <table id="myProjectTable" className="table table-hover align-middle mb-0" style={{ width: '100%' }}>
                                    <thead>
                                        <tr>
                                            <th>Employee ID</th>
                                            <th>Employee Name</th>
                                            <th>Department</th>
                                            <th>Working Days</th>
                                            <th>Date</th>
                                            <th>Salary Amount</th>
                                            <th>Status</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <EmployeeLoanList loans={loans} />
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <AdminSalaryModal show={showModal} handleClose={handleModalClose} loan={selectedLoan} handleSave={handleSave} />
        </div>
    );
};

export default AdminSalary;

