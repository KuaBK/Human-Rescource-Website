import ModalCreateEmployee from "./ModalCreateEmployee";
import './Employee.scss';

const Employee = (props) => {
    return (
        <div className="manage-emp-container">
            <div className="title">
                Manage Personel
            </div> 

            <div className="emp-content">
                <div>
                    <button>Add new employee</button>
                </div>
                <div>
                    table users
                    123lyquang
                </div>
                <ModalCreateEmployee/>
            </div>
        </div>
    )
}

export default Employee;