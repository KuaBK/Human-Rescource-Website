import React, { useState } from 'react';
import { Modal, Button } from 'react-bootstrap';

const AdjustRateModal = ({ show, handleClose, rates, handleSave }) => {
    const [formData, setFormData] = useState(rates);

    const handleChange = (e) => {
        const { name, value } = e.target;
        const updatedFormData = { ...formData, [name]: parseFloat(value) };
        setFormData(updatedFormData);
        console.log(`Updated field: ${name}, Value: ${value}`); // Kiểm tra giá trị mới của trường thay đổi
    };

    
// // liên kết vs PUT API ở đây
// {
//     fullShiftRate : 200
//    halfShiftRate :  100
// }

    const handleSubmit = () => {
        console.log('Final data before save:', formData); // Kiểm tra toàn bộ dữ liệu trước khi lưu
        handleSave(formData);
    };

    return (
        <Modal show={show} onHide={handleClose} centered>
            <Modal.Header closeButton>
                <Modal.Title>Điều chỉnh hệ số</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="mb-3">
                    <label className="form-label">Lương 1 ca</label>
                    <input
                        type="number"
                        className="form-control"
                        name="fullShiftRate"
                        value={formData.fullShiftRate}
                        onChange={handleChange}
                    />
                </div>
                <div className="mb-3">
                    <label className="form-label">Lương nửa ca</label>
                    <input
                        type="number"
                        className="form-control"
                        name="halfShiftRate"
                        value={formData.halfShiftRate}
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

export default AdjustRateModal;
