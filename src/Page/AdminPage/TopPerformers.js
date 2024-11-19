import React, { useEffect, useState } from 'react';
import axios from 'axios';


const TopPerformers = () => {
    const [avatars, setAvatars] = useState([]);

    const performers = [
        { name: 'Luke Short', username: '@Short', performance: '80%' },
        { name: 'John Hard', username: '@rdacre', performance: '70%' },
        { name: 'Paul Rees', username: '@Rees', performance: '77%' },
        { name: 'Rachel Parr', username: '@Parr', performance: '85%' },
        { name: 'Eric Reid', username: '@Eric', performance: '95%' },
        { name: 'Jan Ince', username: '@Ince', performance: '97%' }
    ];

    const fetchRandomAvatars = async () => {
        try {
            const responses = await Promise.all(
                Array(performers.length).fill(null).map(() =>
                    axios.get('https://randomuser.me/api/')
                )
            );
            const fetchedAvatars = responses.map(response => response.data.results[0].picture.large);
            setAvatars(fetchedAvatars);
        } catch (error) {
            console.error('Error fetching avatars:', error);
        }
    };

    useEffect(() => {
        fetchRandomAvatars();
    }, []);

    return (
        <div className="col-md-12" style={{ backgroundColor: '#80EE98' }}>
            <div className="card light-danger-bg " style={{ backgroundColor: '#80EE98' }}>
                <div className="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                    <h6 className="mb-0 fw-bold">Top Performers</h6>
                </div>
                <div className="card-body">
                    <div className="row g-3 align-items-center">
                        <div className="col-md-12 col-lg-4 col-xl-4 col-xxl-2">
                            <p>You have 140 <span className="fw-bold">influencers</span> in your company.</p>
                            <div className="d-flex justify-content-between text-center">
                                <div>
                                    <h3 className="fw-bold">350</h3>
                                    <span className="small">New Task</span>
                                </div>
                                <div>
                                    <h3 className="fw-bold">130</h3>
                                    <span className="small">Task Completed</span>
                                </div>
                            </div>
                        </div>
                        <div className="col-md-12 col-lg-12 col-xl-12 col-xxl-10">
                            <div className="row g-3 row-cols-2 row-cols-sm-3 row-cols-md-3 row-cols-lg-3 row-cols-xl-3 row-cols-xxl-6 row-deck top-perfomer">
                                {performers.map((performer, index) => (
                                    <div className="col" key={index}>
                                        <div className="card shadow">
                                            <div className="card-body text-center d-flex flex-column justify-content-center">
                                                <img className="avatar lg rounded-circle img-thumbnail mx-auto"
                                                    src={avatars[index]} alt="profile" />
                                                <h6 className="fw-bold my-2 small-14">{performer.name}</h6>
                                                <span className="text-muted mb-2">{performer.username}</span>
                                                <h4 className="fw-bold text-primary fs-3">{performer.performance}</h4>
                                            </div>
                                        </div>
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default TopPerformers;
