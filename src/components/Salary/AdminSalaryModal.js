import React, { useState, useEffect } from 'react';
import { Modal, Button } from 'react-bootstrap';
import './AdminSalaryModal.scss';

const AdminSalaryModal = ({ show, handleClose, record, handleSave }) => {
    const [formData, setFormData] = useState({
        bonus: '',
        penalty: '',
        real_pay: '',
    });

    useEffect(() => {
        if (record) {
            setFormData({
                bonus: record.bonus || '',
                penalty: record.penalty || '',
                real_pay: record.real_pay || '',
            });
        }
    }, [record]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prevData) => ({ ...prevData, [name]: value }));
    };

    const handleSubmit = () => {
        if (!formData.real_pay) {
            alert('Vui lòng điền đầy đủ thông tin lương thực tế!');
            return;
        }

        handleSave({
            ...record, // Pass the full record back with updated fields
            bonus: parseFloat(formData.bonus),
            penalty: parseFloat(formData.penalty),
            real_pay: parseFloat(formData.real_pay),
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
