import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './EmployeeAttendance.scss';

const EmployeeAttendance = () => {
    const [attendanceData, setAttendanceData] = useState([]);
    const [selectedMonth, setSelectedMonth] = useState(new Date().getMonth() + 1); 
    const [selectedYear, setSelectedYear] = useState(new Date().getFullYear());
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const employeeCode = localStorage.getItem('personelCode');

    const fetchAttendanceData = async (month, year) => {
        setLoading(true);
        setError(null);

        if (!employeeCode) {
            setError('Employee code not found in local storage.');
            setLoading(false);
            return;
        }

        try {
            const response = await axios.get('http://localhost:8080/api/attendance/all/employee', {
                params: { code: employeeCode },
            });

            const { code, result } = response.data;
            if (code === 1000) {
                const filteredData = result.filter((attendance) => {
                    const attendanceDate = new Date(attendance.date);
                    return (
                        attendanceDate.getMonth() === month - 1 &&
                        attendanceDate.getFullYear() === year
                    );
                });
                setAttendanceData(filteredData);
            } else {
                setError('Failed to fetch attendance records.');
            }
        } catch (err) {
            setError('Error while fetching attendance data.');
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchAttendanceData(selectedMonth, selectedYear);
    }, [selectedMonth, selectedYear]);

    const daysInMonth = Array.from(
        { length: new Date(selectedYear, selectedMonth, 0).getDate() },
        (_, i) => i + 1
    );

    const handleCheckIn = async () => {
        try {
            await axios.post(`http://localhost:8080/api/attendance/checkIn`, null, {
                params: { code: employeeCode },
            });
            alert('Check-in successful!');
          //  fetchAttendanceData(selectedMonth, selectedYear);
        } catch (err) {
            alert('Check-in failed. Please try again.');
        }
    };

    const handleCheckOut = async () => {
        try {
            await axios.post(`http://localhost:8080/api/attendance/checkOut`, null, {
                params: { code: employeeCode },
            });
            alert('Check-out successful!');
           // fetchAttendanceData(selectedMonth, selectedYear);
        } catch (err) {
            alert('Check-out failed. Please try again.');
        }
    };

    const getDayStatus = (day) => {
        const match = attendanceData.find((attendance) => {
            const attendanceDate = new Date(attendance.date);
            return attendanceDate.getDate() === day;
        });

        if (!match) {
            const today = new Date();
            const isFuture = new Date(selectedYear, selectedMonth - 1, day) > today;
            return isFuture ? { color: 'transparent', text: '' } : { color: '#ff4d4f', text: 'Absent' };
        }

        const durationMinutes = calculateDurationInMinutes(match.duration);
        const durationStatus = durationMinutes > 25 ? 'Full work' : 'Half work';
        const color = durationStatus === 'Full work' ? '#52c41a' : '#faad14';

        return { color, text: durationStatus };
    };

    const calculateDurationInMinutes = (duration) => {
        const [hours, minutes] = duration.split(':').map(Number);
        return hours * 60 + minutes;
    };

    return (
        <div className="employee-attendance">
            <div className="header">
                <h2 className="title">My Attendance</h2>
                <div className="filters">
                    <label>Month:</label>
                    <select
                        value={selectedMonth}
                        onChange={(e) => setSelectedMonth(parseInt(e.target.value))}
                    >
                        {[...Array(12).keys()].map((i) => (
                            <option key={i + 1} value={i + 1}>
                                {i + 1}
                            </option>
                        ))}
                    </select>
                    <label>Year:</label>
                    <input
                        type="number"
                        value={selectedYear}
                        onChange={(e) => setSelectedYear(parseInt(e.target.value))}
                    />
                </div>
                <div className="actions">
                    <button onClick={handleCheckIn} className="check-in">Check-In</button>
                    <button onClick={handleCheckOut} className="check-out">Check-Out</button>
                </div>
            </div>

            <div className="calendar">
                <div className="calendar-row">
                    {daysInMonth.map((day) => {
                        const { color, text } = getDayStatus(day);
                        return (
                            <div
                                key={day}
                                className="calendar-day"
                                style={{
                                    backgroundColor: color,
                                    borderRadius: '8px',
                                    color: color === 'transparent' ? '#000' : '#fff',
                                }}
                            >
                                <div className="day-number">{day}</div>
                                <div className="day-status">{text}</div>
                            </div>
                        );
                    })}
                </div>
            </div>
        </div>
    );
};

export default EmployeeAttendance;
