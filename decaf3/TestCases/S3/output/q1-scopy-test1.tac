VTABLE(_A) {
    <empty>
    A
    _A.seta;
    _A.getA;
}

VTABLE(_Father) {
    <empty>
    Father
    _Father.setfield;
    _Father.seta;
    _Father.getA;
}

VTABLE(_Test) {
    <empty>
    Test
    _Test.test;
}

VTABLE(_Main) {
    <empty>
    Main
}

FUNCTION(_A_New) {
memo ''
_A_New:
    _T9 = 8
    parm _T9
    _T10 =  call _Alloc
    _T11 = 0
    *(_T10 + 4) = _T11
    _T12 = VTBL <_A>
    *(_T10 + 0) = _T12
    return _T10
}

FUNCTION(_Father_New) {
memo ''
_Father_New:
    _T13 = 12
    parm _T13
    _T14 =  call _Alloc
    _T15 = 0
    *(_T14 + 4) = _T15
    *(_T14 + 8) = _T15
    _T16 = VTBL <_Father>
    *(_T14 + 0) = _T16
    return _T14
}

FUNCTION(_Test_New) {
memo ''
_Test_New:
    _T17 = 4
    parm _T17
    _T18 =  call _Alloc
    _T19 = VTBL <_Test>
    *(_T18 + 0) = _T19
    return _T18
}

FUNCTION(_Main_New) {
memo ''
_Main_New:
    _T20 = 4
    parm _T20
    _T21 =  call _Alloc
    _T22 = VTBL <_Main>
    *(_T21 + 0) = _T22
    return _T21
}

FUNCTION(_A.seta) {
memo '_T0:4 _T1:8'
_A.seta:
    _T23 = *(_T0 + 4)
    *(_T0 + 4) = _T1
}

FUNCTION(_A.getA) {
memo '_T2:4'
_A.getA:
    _T24 = *(_T2 + 4)
    return _T24
}

FUNCTION(_Father.setfield) {
memo '_T3:4 _T4:8'
_Father.setfield:
    _T25 = *(_T3 + 4)
    *(_T3 + 4) = _T4
    _T26 = *(_T3 + 8)
    _T27 =  call _A_New
    *(_T3 + 8) = _T27
}

FUNCTION(_Father.seta) {
memo '_T5:4 _T6:8'
_Father.seta:
    _T28 = *(_T5 + 8)
    parm _T28
    parm _T6
    _T29 = *(_T28 + 0)
    _T30 = *(_T29 + 8)
    call _T30
}

FUNCTION(_Father.getA) {
memo '_T7:4'
_Father.getA:
    _T31 = *(_T7 + 8)
    parm _T31
    _T32 = *(_T31 + 0)
    _T33 = *(_T32 + 12)
    _T34 =  call _T33
}

FUNCTION(_Test.test) {
memo '_T8:4'
_Test.test:
    _T37 =  call _Father_New
    _T36 = _T37
    _T38 = 5
    parm _T36
    parm _T38
    _T39 = *(_T36 + 0)
    _T40 = *(_T39 + 8)
    call _T40
    _T41 = 10
    parm _T36
    parm _T41
    _T42 = *(_T36 + 0)
    _T43 = *(_T42 + 12)
    call _T43
    _T44 =  call _Father_New
    _T45 = *(_T36 + 4)
    *(_T44 + 4) = _T45
    _T46 = *(_T36 + 8)
    *(_T44 + 8) = _T46
    parm _T36
    _T47 = *(_T36 + 0)
    _T48 = *(_T47 + 16)
    _T49 =  call _T48
    parm _T49
    call _PrintInt
    parm _T44
    _T50 = *(_T44 + 0)
    _T51 = *(_T50 + 16)
    _T52 =  call _T51
    parm _T52
    call _PrintInt
    _T53 = 5
    parm _T36
    parm _T53
    _T54 = *(_T36 + 0)
    _T55 = *(_T54 + 12)
    call _T55
    parm _T36
    _T56 = *(_T36 + 0)
    _T57 = *(_T56 + 16)
    _T58 =  call _T57
    parm _T58
    call _PrintInt
    parm _T44
    _T59 = *(_T44 + 0)
    _T60 = *(_T59 + 16)
    _T61 =  call _T60
    parm _T61
    call _PrintInt
}

FUNCTION(main) {
memo ''
main:
    _T62 =  call _Test_New
    parm _T62
    _T63 = *(_T62 + 0)
    _T64 = *(_T63 + 8)
    call _T64
}

