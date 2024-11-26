import React, { useState } from 'react';
import { NotePencil } from 'phosphor-react';
import AdminSalaryModal from './AdminSalaryModal';
import AdjustRateModal from './AdjustRateModal';
import './AdminSalary.scss';

const AdminSalary = () => {
    const [showModal, setShowModal] = useState(false);
    const [selectedRecord, setSelectedRecord] = useState(null);
    const [showAdjustRateModal, setShowAdjustRateModal] = useState(false);
    const [rates, setRates] = useState({ fullShiftRate: 200, halfShiftRate: 100 });

    const [filterMonth, setFilterMonth] = useState('');
    const [filterYear, setFilterYear] = useState('');

    const [Salary, setSalary] = useState([
        { emp_code: 'LP-0101', id: 1, month: 1, year: 2022, bonus: 500, penalty: 100, real_pay: 3900, full_work: 22, half_work: 0, absence: 1, name: 'Joan Dyer', image: 'https://randomuser.me/api/portraits/men/1.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
        { emp_code: 'LP-2007', id: 2, month: 1, year: 2023, bonus: 500, penalty: 100, real_pay: 3900, full_work: 22, half_work: 0, absence: 1, name: 'Ly Quang', image: 'https://randomuser.me/api/portraits/men/2.jpg' },
        { emp_code: 'LP-3008', id: 3, month: 2, year: 2023, bonus: 400, penalty: 200, real_pay: 3600, full_work: 20, half_work: 2, absence: 2, name: 'Jane Smith', image: 'https://randomuser.me/api/portraits/women/1.jpg' },
    ]);

    const handleModalOpen = (record) => {
        setSelectedRecord(record);
        setShowModal(true);
    };

    const handleModalClose = () => {
        setShowModal(false);
        setSelectedRecord(null);
    };

    const handleSave = (updatedRecord) => {
        setSalary((prevSalary) =>
            prevSalary.map((record) => (record.id === updatedRecord.id ? updatedRecord : record))
        );
    };

    const handleAdjustRateModalClose = () => {
        setShowAdjustRateModal(false);
    };

    const handleRateSave = async (updatedRates) => {
        try {
            // API call to save updated rates
            // const response = await axios.post('/api/salaries/rates', updatedRates);
           // console.log(response.data); // Optionally log the response from the backend
    
            // Update local state after successful save
            setRates(updatedRates);
            setShowAdjustRateModal(false);
        } catch (error) {
            console.error("Failed to update rates:", error);
        }
    };
    

    // Filter the salary records based on the selected month and year
    const filteredSalary = Salary.filter((record) => {
        const monthMatch = filterMonth ? record.month === parseInt(filterMonth) : true;
        const yearMatch = filterYear ? record.year === parseInt(filterYear) : true;
        return monthMatch && yearMatch;
    });

    return (
        <div className="admin-salary-container">
            <h3 className="title">Bảng lương nhân viên</h3>

            {/* Filters for Month and Year */}
            <div className="filters">
                <div className="filter-group">
                    <label htmlFor="month">Tháng:</label>
                    <select
                        id="month"
                        value={filterMonth}
                        onChange={(e) => setFilterMonth(e.target.value)}
                    >
                        <option value="">Tất cả</option>
                        {[...Array(12).keys()].map((m) => (
                            <option key={m + 1} value={m + 1}>
                                {m + 1}
                            </option>
                        ))}
                    </select>
                </div>
                <div className="filter-group">
                    <label htmlFor="year">Năm:</label>
                    <select
                        id="year"
                        value={filterYear}
                        onChange={(e) => setFilterYear(e.target.value)}
                    >
                        <option value="">Tất cả</option>
                        {[...new Set(Salary.map((r) => r.year))].map((year) => (
                            <option key={year} value={year}>
                                {year}
                            </option>
                        ))}
                    </select>
                </div>
            </div>

            <div className="salary-table">
                <table className="table table-hover">
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
                            <th>Chỉnh sửa</th>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredSalary.map((record, index) => (
                            <tr key={index}>
                                <td>{record.emp_code}</td>
                                <td className="employee-info">
                                    <img src={record.image} alt="avatar" className="avatar" />
                                    <span>{record.name}</span>
                                </td>
                                <td>{record.month}</td>
                                <td>{record.year}</td>
                                <td>{record.full_work}</td>
                                <td>{record.half_work}</td>
                                <td>{record.absence}</td>
                                <td className="text-success">{record.bonus}Đ</td>
                                <td className="text-danger">{record.penalty}Đ</td>
                                <td className="text-success">{record.real_pay}Đ</td>
                                <td>
                                    <button
                                        className="btn-edit"
                                        onClick={() => handleModalOpen(record)}
                                    >
                                        <NotePencil size={20} />
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            <button
                className="btn-adjust-rate"
                onClick={() => setShowAdjustRateModal(true)}
            >
                Điều chỉnh hệ số
            </button>
            <AdminSalaryModal
                show={showModal}
                handleClose={handleModalClose}
                record={selectedRecord}
                handleSave={handleSave}
            />
            <AdjustRateModal
                show={showAdjustRateModal}
                handleClose={handleAdjustRateModalClose}
                rates={rates}
                handleSave={handleRateSave}
            />
        </div>
    );
};

export default AdminSalary;
