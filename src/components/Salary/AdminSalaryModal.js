import React, { useState, useEffect } from 'react';
import { Modal, Button } from 'react-bootstrap';
import './AdminSalaryModal.scss';

const AdminSalaryModal = ({ show, handleClose, record, handleSave }) => {
    const [formData, setFormData] = useState({
        month: '',
        year: '',
        bonus: '',
        penalty: '',
        real_pay: '',
        full_work: '',
        half_work: '',
        absence: '',
    });

    useEffect(() => {
        if (record) {
            setFormData({
                month: record.month || '',
                year: record.year || '',
                bonus: record.bonus || '',
                penalty: record.penalty || '',
                real_pay: record.real_pay || '',
                full_work: record.full_work || '',
                half_work: record.half_work || '',
                absence: record.absence || '',
            });
        }
    }, [record]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    // báo lỗi khi thiếu một trong 3 trường
    const handleSubmit = () => {
        if (!formData.month || !formData.year || !formData.real_pay) {
            alert('Vui lòng điền đầy đủ thông tin !');
            return;
        }

        handleSave({
            ...record,
            ...formData,
            month: parseInt(formData.month, 10),
            year: parseInt(formData.year, 10),
            bonus: parseFloat(formData.bonus),
            penalty: parseFloat(formData.penalty),
            real_pay: parseFloat(formData.real_pay),
            full_work: parseInt(formData.full_work, 10),
            half_work: parseInt(formData.half_work, 10),
            absence: parseInt(formData.absence, 10),
        });

        handleClose();
    };

    return (
        <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Sửa lương lại cho {record?.name}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="mb-3">
                    <label className="form-label">Tháng</label>
                    <input
                        type="number"
                        className="form-control"
                        name="month"
                        value={formData.month}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Năm</label>
                    <input
                        type="number"
                        className="form-control"
                        name="year"
                        value={formData.year}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Số ngày làm đủ 1 ca</label>
                    <input
                        type="number"
                        className="form-control"
                        name="full_work"
                        value={formData.full_work}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Số ngày làm nửa ca</label>
                    <input
                        type="number"
                        className="form-control"
                        name="half_work"
                        value={formData.half_work}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Số ngày vắng</label>
                    <input
                        type="number"
                        className="form-control"
                        name="absence"
                        value={formData.absence}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Tiền thưởng</label>
                    <input
                        type="number"
                        className="form-control"
                        name="bonus"
                        value={formData.bonus}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Phạt</label>
                    <input
                        type="number"
                        className="form-control"
                        name="penalty"
                        value={formData.penalty}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Lương thực tế nhận được</label>
                    <input
                        type="number"
                        className="form-control"
                        name="real_pay"
                        value={formData.real_pay}
                        onChange={handleChange}
                    />
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={handleClose}>
                    Đóng
                </Button>
                <Button variant="primary" onClick={handleSubmit}>
                    Lưu thay đổi
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default AdminSalaryModal;
